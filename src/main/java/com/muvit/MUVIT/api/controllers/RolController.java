package com.muvit.MUVIT.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.muvit.MUVIT.api.dto.request.LoginRolRequest;
import com.muvit.MUVIT.api.dto.request.RegisterDriverReq;
import com.muvit.MUVIT.api.dto.request.RegisterReq;
import com.muvit.MUVIT.api.dto.request.RegisterRolRequest;
import com.muvit.MUVIT.api.dto.request.RegisterUserReq;
import com.muvit.MUVIT.api.dto.request.RolRequest;
import com.muvit.MUVIT.api.dto.response.AuthResp;
import com.muvit.MUVIT.api.dto.response.RolResponse;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IAuthService;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IRolService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
public class RolController {
    @Autowired
    private final IRolService rolService;
    private final IAuthService IAuthService;


    @PostMapping(path="/auth/login")
    public ResponseEntity<AuthResp> login(@Validated @RequestBody LoginRolRequest request) {
        return ResponseEntity.ok(this.IAuthService.login(request));
    }
    @PostMapping(path="/auth/register")
    public ResponseEntity<AuthResp> register(@Validated @RequestBody RegisterReq request) {
        return ResponseEntity.ok(this.IAuthService.register(request));
    }
    @PostMapping(path= "/auth/register/driver")
    public ResponseEntity<AuthResp> registerDriver( @Validated @RequestBody RegisterDriverReq request) {

        return ResponseEntity.ok(this.IAuthService.registerDriver(request));
    }
    @PostMapping(path= "/auth/register/user")
    public ResponseEntity<AuthResp> registerEmployee( @Validated @RequestBody RegisterUserReq request) {

        return ResponseEntity.ok(this.IAuthService.registerUser(request));
    }

    
    
    @GetMapping(path = "/auth/")
    public ResponseEntity<Page<RolResponse>> get(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size) {

        return ResponseEntity.ok(this.rolService.getAll(page - 1, size));
    }

    @GetMapping(path = "/auth/{id}")
    public ResponseEntity<RolResponse> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.rolService.getById(id));
    }

    // @PostMapping(path = "")
    //  public ResponseEntity<RolResponse> insert(
    //          @Validated @RequestBody RolRequest rol) {
    //     return ResponseEntity.ok(this.rolService.create(rol));
    //  }

    @DeleteMapping(path = "/auth/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.rolService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/auth/{id}")
    public ResponseEntity<RolResponse> update(
            @PathVariable Long id, // id por url
            @Validated @RequestBody RolRequest company // compañia actualizada
    ) {
        return ResponseEntity.ok(this.rolService.update(id, company));
        
    }
    
}
