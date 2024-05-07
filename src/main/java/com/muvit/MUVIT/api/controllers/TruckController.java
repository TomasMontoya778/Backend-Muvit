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

import com.muvit.MUVIT.api.dto.request.TruckRequest;
import com.muvit.MUVIT.api.dto.response.TruckResponse;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.ITruckService;

import lombok.AllArgsConstructor;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/truck")
@AllArgsConstructor
public class TruckController {
    @Autowired
    private final ITruckService truckService;
        @GetMapping
    public ResponseEntity<Page<TruckResponse>> get(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size) {

        return ResponseEntity.ok(this.truckService.getAll(page - 1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TruckResponse> getById(
            @PathVariable String id) {
        return ResponseEntity.ok(this.truckService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TruckResponse> insert(
            @Validated @RequestBody TruckRequest company) {
        return ResponseEntity.ok(this.truckService.create(company));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.truckService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<TruckResponse> update(
            @PathVariable String id, // id por url
            @Validated @RequestBody TruckRequest company // compañia actualizada
    ) {
        return ResponseEntity.ok(this.truckService.update(id, company));
    }
    
}
