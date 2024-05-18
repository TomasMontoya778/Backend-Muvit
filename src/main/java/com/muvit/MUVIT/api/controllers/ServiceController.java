package com.muvit.MUVIT.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import com.muvit.MUVIT.api.dto.request.ServiceRequest;
import com.muvit.MUVIT.api.dto.response.ServiceResponse;
import com.muvit.MUVIT.api.error_handler.response.ErrorResponse;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IServiceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/service")
@AllArgsConstructor
@Tag(name = "Endpoints Service")
public class ServiceController {
    @Autowired
    private final IServiceService serviceService;

    @Operation(summary = "This EndPoint gets all registered sevices, it also has a paging function that only displays depending on the page and listing size.", description = "you must send the page and corresponding size to list the services")
    @GetMapping
    public ResponseEntity<Page<ServiceResponse>> get(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size) {

        return ResponseEntity.ok(this.serviceService.getAll(page - 1, size));
    }

    @ApiResponse(responseCode = "400", description = "When the ID is wrong.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(summary = "This EndPoint gets a service depending his ID.", description = "Just have send an ID, and if that ID is same as service's ID, It is obtained and It will be displayed.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<ServiceResponse> getById(
            @PathVariable String id) {
        return ResponseEntity.ok(this.serviceService.getById(id));
    }

    @Operation(summary = "This EndPoint insert a service depending his requirements and params.", description = "Just have send all the params required for the service, but first, you have to insert a user because always the services needs to an User.")
    @PostMapping
    public ResponseEntity<ServiceResponse> insert(
            @Validated @RequestBody ServiceRequest serviceRequest) {
        return ResponseEntity.ok(this.serviceService.create(serviceRequest));
    }

    @Operation(summary = "This EndPoint delete a service depending his ID", description = "Just have send an ID, and if that ID is same as service's ID, It will be eliminated.")
    @ApiResponse(responseCode = "400", description = "When the ID is wrong.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.serviceService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @ApiResponse(responseCode = "400", description = "When the ID is wrong.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(summary = "This EndPoint update a service depending his ID", description = "Just have send an ID, and if that ID is same as service's ID, It will be update, but you have to send the news params as in the insert endpoint.")
    @PutMapping(path = "/{id}")
    public ResponseEntity<ServiceResponse> update(
            @PathVariable String id,
            @Validated @RequestBody ServiceRequest serviceRequest) {
        return ResponseEntity.ok(this.serviceService.update(id, serviceRequest));
    }

    @ApiResponse(responseCode = "400", description = "When the user's ID is wrong.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(summary = "This endpoint retrieves an ACTIVE service of a user.", description = "A user can only have one active service at a time, so it will fetch the active service based on the user ID.")
    @GetMapping(path = "/user/{userId}/active-service")
    public ResponseEntity<ServiceResponse> getActiveServiceByUserId(@Validated @PathVariable String userId) {
        Optional<ServiceResponse> serviceResponse = this.serviceService.getActiveServiceByUserId(userId);
        return serviceResponse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @ApiResponse(responseCode = "400", description = "When the user's ID is wrong.", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(summary = "This endpoint retrieves all inactive services of a user.", description = "A user can have multiple inactive services as their requested services are completed, so it will fetch the inactive services based on the user ID and whether the user has inactive services; This works to implement a service history system with pagination functionality. For this, you need to input the page size and the number of services you want to list.")
    @GetMapping(path = "/user/{userId}/inactive-service")
    public ResponseEntity<Page<ServiceResponse>> getInactiveServiceByUserId(@Validated @PathVariable String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(this.serviceService.getInactiveServiceByUserId(userId, pageable));
    }
}
