package com.muvit.MUVIT.api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.muvit.MUVIT.api.dto.request.RolRequest;
import com.muvit.MUVIT.api.dto.response.RolResponse;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IRolService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rol")
@AllArgsConstructor
public class RolController {
    @Autowired
    private final IRolService rolService;

    @GetMapping
    public ResponseEntity<Page<RolResponse>> get(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size) {

        return ResponseEntity.ok(this.rolService.getAll(page - 1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RolResponse> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.rolService.getById(id));
    }

    @PostMapping
    public ResponseEntity<RolResponse> insert(
            @Validated @RequestBody RolRequest rol) {
        return ResponseEntity.ok(this.rolService.create(rol));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.rolService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<RolResponse> update(
            @PathVariable Long id, // id por url
            @Validated @RequestBody RolRequest company // compañia actualizada
    ) {
        return ResponseEntity.ok(this.rolService.update(id, company));
        
    }
}
