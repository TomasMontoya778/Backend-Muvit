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

import com.muvit.MUVIT.api.dto.request.ServiceRequest;
import com.muvit.MUVIT.api.dto.response.ServiceResponse;

import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IServiceService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/service")
@AllArgsConstructor
public class ServiceController {
    @Autowired
    private final IServiceService serviceService;
        @GetMapping
    public ResponseEntity<Page<ServiceResponse>> get(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size) {

        return ResponseEntity.ok(this.serviceService.getAll(page - 1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ServiceResponse> getById(
            @PathVariable String id) {
        return ResponseEntity.ok(this.serviceService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ServiceResponse> insert(
            @Validated @RequestBody ServiceRequest company) {
        return ResponseEntity.ok(this.serviceService.create(company));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.serviceService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ServiceResponse> update(
            @PathVariable String id, // id por url
            @Validated @RequestBody ServiceRequest company // compañia actualizada
    ) {
        return ResponseEntity.ok(this.serviceService.update(id, company));
    }
}
