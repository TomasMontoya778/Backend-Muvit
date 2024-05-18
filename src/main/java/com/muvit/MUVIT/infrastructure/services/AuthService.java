package com.muvit.MUVIT.infrastructure.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.muvit.MUVIT.api.dto.request.LoginRolRequest;
import com.muvit.MUVIT.api.dto.request.RegisterDriverReq;
import com.muvit.MUVIT.api.dto.request.RegisterReq;
import com.muvit.MUVIT.api.dto.request.RegisterUserReq;
import com.muvit.MUVIT.api.dto.response.AuthResp;
import com.muvit.MUVIT.domain.entities.Driver;
import com.muvit.MUVIT.domain.entities.Rol;
import com.muvit.MUVIT.domain.entities.User;
import com.muvit.MUVIT.domain.repositories.DriverRepository;
import com.muvit.MUVIT.domain.repositories.RolRepository;
import com.muvit.MUVIT.domain.repositories.UserRepository;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IAuthService;
import com.muvit.MUVIT.infrastructure.helpers.JwtService;
import com.muvit.MUVIT.util.enums.DNITypeEnum;
import com.muvit.MUVIT.util.enums.RolEnum;
import com.muvit.MUVIT.util.exceptions.BadRequestException;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AuthService implements IAuthService{
    
    
    @Autowired
    private final UserRepository objUserRepository;
    @Autowired
    private final RolRepository objRolRepository;
    @Autowired
    private final DriverRepository objDriverRepository;
    @Autowired
    private final JwtService objJwtService;
    
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResp login(LoginRolRequest request) {
     try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getNameUser(), request.getPassword()));
     } catch (Exception e) {
        
        throw new BadRequestException("Credenciales incorrectas");
     }   

     Rol objRol = this.findUser(request.getNameUser());

     return  AuthResp.builder()
     .message("Autenticado correctamente")
     .token(this.objJwtService.getToken(objRol))
     .build();
    }

    @Override
    public AuthResp register(RegisterReq request) {
        /*1. Validar que el usuario no exista */
        Rol objRol = this.findUser(request.getNameUser());
        if (objRol != null) throw new BadRequestException("El usuario ya existe");
/*
 * Construir el usuario
 */

        Rol rol = Rol.builder()
        .nameUser(request.getNameUser())
        .password(this.passwordEncoder.encode(request.getPassword()))
        .rolEnum(RolEnum.Admin)
        .build();

        rol = this.objRolRepository.save(rol);

        return AuthResp.builder()
        .message("registro exitoso")
        .token(this.objJwtService.getToken(rol))
        .build();
    }
    
    private Rol findUser(String userName){

        return this.objRolRepository.findByNameUser(userName).orElse(null);

    }

    @Override
    public AuthResp registerDriver(RegisterDriverReq request) {
        Rol objRol = validatedUser(request.getNameUser(), request.getPassword(), RolEnum.Driver);

        objRol = this.objRolRepository.save(objRol);

            Driver objDriver =  Driver.builder()
        .name(request.getName())
        .lastName(request.getLastName())
        .email(request.getEmail())
        .phoneNumber(request.getPhoneNumber())
        .DNI_type(DNITypeEnum.valueOf(request.getDNI_type()))
        .DNI(request.getDNI())
        .truck(new ArrayList<>())
        .rol(objRol)
        .assistants(new ArrayList<>())
        .build();

        this.objDriverRepository.save(objDriver);
        return AuthResp
        .builder()
        .message("Driver creado")
        .token(this.objJwtService.getToken(objRol))
        .build();

    }

    public AuthResp registerUser(RegisterUserReq request){
        Rol objRol = validatedUser(request.getNameUser(), request.getPassword(), RolEnum.User);

        objRol = this.objRolRepository.save(objRol);

        User client = User.builder()
        .email(request.getEmail())
        .rol(objRol)
        .phoneNumber(request.getPhoneNumber())
        .name(request.getName())
        .lastName(request.getLastName())
        .userService(new ArrayList<>())
        .build();

        this.objUserRepository.save(client);

        return AuthResp
        .builder()
        .message("Cliente creado")
        .token(this.objJwtService.getToken(objRol))
        .build();
    }
    private Rol validatedUser(String username, String password, RolEnum rol){
        Rol objRol = this.findUser(username);
        if (objRol != null) throw new BadRequestException("El usuario ya existe");

        return Rol.builder()
        .nameUser(username)
        .password(this.passwordEncoder.encode(password))
        .rolEnum(rol)
        .build();

    }
    



}
