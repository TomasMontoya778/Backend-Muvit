package com.muvit.MUVIT.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muvit.MUVIT.api.dto.request.AssistReq;
import com.muvit.MUVIT.api.dto.response.AssistRes;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IAssisService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@AllArgsConstructor
@RequestMapping("/assistant")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssisController {

    @Autowired
    private final IAssisService objService;

    @GetMapping
    public ResponseEntity<Page <AssistRes>> getAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "2") int size
        ) {
        
        return ResponseEntity.ok(this.objService.getAll(page -1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AssistRes> getById(@PathVariable String id) {
        return ResponseEntity.ok(this.objService.getById(id));
    }
    
    @PostMapping
    public ResponseEntity<AssistRes> insert(
        @Validated @RequestBody AssistReq assistReq) {
        
        return ResponseEntity.ok(this.objService.create(assistReq));
       
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(
        @PathVariable String id
    ){
    this.objService.delete(id);

    return ResponseEntity.noContent().build();
    
    }
    @PutMapping(path="/{id}")
    public ResponseEntity<AssistRes> update(@Validated @PathVariable String id, @RequestBody AssistReq entity) {
        return ResponseEntity.ok(this.objService.update(id, entity));
    }
    
    

}
