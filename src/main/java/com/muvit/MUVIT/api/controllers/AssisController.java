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
import com.muvit.MUVIT.api.error_handler.response.ErrorResponse;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IAssisService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Endpoints Assistant")
public class AssisController {

    @Autowired
    private final IAssisService objService;
    @Operation(summary = "This EndPoint gets all registered assistants, it also has a paging function that only displays depending on the page and listing size.", description = "you must send the page and corresponding size to list the assistants")
    @GetMapping
    public ResponseEntity<Page <AssistRes>> getAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "2") int size
        ) {
        
        return ResponseEntity.ok(this.objService.getAll(page -1, size));
    }
    @ApiResponse(responseCode = "400", description = "When the ID is wrong.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(summary = "This EndPoint gets an assistant depending his ID.", description = "Just have send an ID, and if that ID is same as assistant's ID, It is obtained and It will be displayed.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<AssistRes> getById(@PathVariable String id) {
        return ResponseEntity.ok(this.objService.getById(id));
    }
    @Operation(summary = "This EndPoint insert an assistant depending his requirements and params.", description = "Just have send all the params required for the assistant and contact data.")
    @PostMapping
    public ResponseEntity<AssistRes> insert(
        @Validated @RequestBody AssistReq assistReq) {
        
        return ResponseEntity.ok(this.objService.create(assistReq));
       
    }
    @Operation(summary = "This EndPoint delete an assistant depending his ID", description = "Just have send an ID, and if that ID is same as assistant's ID, It will be eliminated.")
    @ApiResponse(responseCode = "400", description = "When the ID is wrong.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(
        @PathVariable String id
    ){
    this.objService.delete(id);

    return ResponseEntity.noContent().build();
    
    }

    
    @ApiResponse(responseCode = "400", description = "When the ID is wrong.", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
})
    @Operation(summary = "This EndPoint update an user depending his ID", description = "Just have send an ID, and if that ID is same as user's ID, It will be update, but you have to send the news params.")
    @PutMapping(path="/{id}")
    public ResponseEntity<AssistRes> update(@Validated @PathVariable String id, @RequestBody AssistReq entity) {
        return ResponseEntity.ok(this.objService.update(id, entity));
    }
    
    

}
