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

import com.muvit.MUVIT.api.dto.request.UserRequest;
import com.muvit.MUVIT.api.dto.response.UserResponse;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IUserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    @Autowired
    private final IUserService userService;
        @GetMapping
    public ResponseEntity<Page<UserResponse>> get(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size) {

        return ResponseEntity.ok(this.userService.getAll(page - 1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> getById(
            @PathVariable String id) {
        return ResponseEntity.ok(this.userService.getById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> insert(
            @Validated @RequestBody UserRequest company) {
        return ResponseEntity.ok(this.userService.create(company));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.userService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable String id, // id por url
            @Validated @RequestBody UserRequest company // compañia actualizada
    ) {
        return ResponseEntity.ok(this.userService.update(id, company));
    }


}
