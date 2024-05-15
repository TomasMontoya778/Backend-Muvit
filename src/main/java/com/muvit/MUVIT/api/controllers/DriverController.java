package com.muvit.MUVIT.api.controllers;

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

import com.muvit.MUVIT.api.dto.request.DriverRequest;
import com.muvit.MUVIT.api.dto.response.DriverResponse;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IDriverService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/driver")
@AllArgsConstructor
public class DriverController {
    @Autowired
    private final IDriverService driverService;

    @GetMapping
    public ResponseEntity<Page<DriverResponse>> get(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(this.driverService.getAll(page - 1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DriverResponse> getById(
            @PathVariable String id) {
        return ResponseEntity.ok(this.driverService.getById(id));
    }

    @PostMapping
    public ResponseEntity<DriverResponse> insert(
            @Valid @RequestBody DriverRequest driver) {
        return ResponseEntity.ok(this.driverService.create(driver));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.driverService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<DriverResponse> update(
            @PathVariable String id, // id por url
            @Validated @RequestBody DriverRequest company // compañia actualizada
    ) {
        return ResponseEntity.ok(this.driverService.update(id, company));
    }
}
