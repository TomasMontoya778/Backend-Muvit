package com.muvit.MUVIT.infrastructure.services;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.muvit.MUVIT.api.dto.request.UserRequest;
import com.muvit.MUVIT.api.dto.response.RolResponse;
import com.muvit.MUVIT.api.dto.response.UserResponse;
import com.muvit.MUVIT.domain.entities.Rol;
import com.muvit.MUVIT.domain.entities.User;
import com.muvit.MUVIT.domain.repositories.RolRepository;
import com.muvit.MUVIT.domain.repositories.UserRepository;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IUserService;
import com.muvit.MUVIT.util.exceptions.BadRequestException;
import com.muvit.MUVIT.util.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    @Autowired
    private final UserRepository objUserRepository;
    @Autowired
    private final RolRepository objRolRepository;

    @Override
    public UserResponse getById(String id) {
        return this.entityToResponse(this.find(id));
    }

    private User find(String id) {
        return this.objUserRepository.findById(id).orElseThrow(() -> new BadRequestException("No hay registros con el ID suministrado"));
    }

    @Override
    public UserResponse create(UserRequest request) {
        User objUser = this.requestToEntity(request, new User());
        return this.entityToResponse(this.objUserRepository.save(objUser));
    }

    private UserResponse entityToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        RolResponse rol = new RolResponse();
        BeanUtils.copyProperties(user.getRol(), rol);
        userResponse.setRol(rol);
        return userResponse;
    }

    public Rol responseRolToEntity(RolResponse rolResponse) {
        Rol rol = new Rol();
        rol.setId_rol(rolResponse.getId_rol());
        rol.setNameUser(rolResponse.getNameUser());
        rol.setPassword(rolResponse.getPassword());
        rol.setRol_enum(rolResponse.getRol());
        return rol;
    }

    private User requestToEntity(UserRequest userRequest, User user) {
        Rol rol = this.objRolRepository.findById(userRequest.getRol())
                .orElseThrow(() -> new IdNotFoundException("Rol"));
        user.setName(userRequest.getName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setRol(rol);
        System.out.println(user);
        user.setUserService(new ArrayList<>());
        return user;
    }

    @Override
    public void delete(String id) {
        User user = this.find(id);

        this.objUserRepository.delete(user);
    }

    @Override
    public Page<UserResponse> getAll(int page, int size) {
        if (page < 0)
            page = 0;
        PageRequest pageRequest = PageRequest.of(page, size);
        return this.objUserRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    public UserResponse update(String id, UserRequest request) {
        User objUser = this.find(id);
        User objUserUpdate = this.requestToEntity(request, objUser);
        return this.entityToResponse(this.objUserRepository.save(objUserUpdate));
    }

}
