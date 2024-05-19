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
import com.muvit.MUVIT.api.error_handler.response.ErrorResponse;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.ITruckService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/truck")
@AllArgsConstructor
@Tag(name = "Endpoints Truck")
public class TruckController {
    @Autowired
    private final ITruckService truckService;
    @Operation(summary = "This EndPoint gets all registered trucks, it also has a paging function that only displays depending on the page and listing size.", description = "you must send the page and corresponding size to list the trucks")
    @GetMapping
    public ResponseEntity<Page<TruckResponse>> get(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size) {

        return ResponseEntity.ok(this.truckService.getAll(page - 1, size));
    }

    @ApiResponse(responseCode = "400", description = "When the ID is wrong.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(summary = "This EndPoint gets a truck depending his ID.", description = "Just have send an ID, and if that ID is same as truck's ID, It is obtained and It will be displayed.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<TruckResponse> getById(
            @PathVariable String id) {
        return ResponseEntity.ok(this.truckService.getById(id));
    }

    @Operation(summary = "This EndPoint insert a truck depending his requirements and params.", description = "Just have send all the params required for the truck.")
    @PostMapping
    public ResponseEntity<TruckResponse> insert(
            @Validated @RequestBody TruckRequest truckRequest) {
        return ResponseEntity.ok(this.truckService.create(truckRequest));
    }

    @ApiResponse(responseCode = "400", description = "When the ID is wrong.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(summary = "This EndPoint gets a truck depending his ID.", description = "Just have send an ID, and if that ID is same as truck's ID, It will be eliminated.")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.truckService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiResponse(responseCode = "400", description = "When the ID is wrong.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(summary = "This EndPoint update a truck depending his ID", description = "Just have send an ID, and if that ID is same as truck's ID, It will be update, but you have to send the news params.")
    @PutMapping(path = "/{id}")
    public ResponseEntity<TruckResponse> update(
            @PathVariable String id,
            @Validated @RequestBody TruckRequest truckRequest) {
        return ResponseEntity.ok(this.truckService.update(id, truckRequest));
    }

}
