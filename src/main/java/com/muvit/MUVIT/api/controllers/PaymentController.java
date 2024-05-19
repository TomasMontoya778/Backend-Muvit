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

import com.muvit.MUVIT.api.dto.request.PaymentRequest;
import com.muvit.MUVIT.api.dto.response.PaymentResponse;
import com.muvit.MUVIT.api.error_handler.response.ErrorResponse;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IPayment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/payment")
@AllArgsConstructor
@Tag(name = "Endpoints Payment")
public class PaymentController {
    @Autowired
    private final IPayment paymentService;

    @Operation(summary = "This EndPoint gets all registered payment methods for the user, it also has a paging function that only displays depending on the page and listing size.", description = "you must send the page and corresponding size to list the roles")
    @GetMapping
    public ResponseEntity<Page<PaymentResponse>> get(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size) {

        return ResponseEntity.ok(this.paymentService.getAll(page - 1, size));
    }

    @ApiResponse(responseCode = "400", description = "When the ID is wrong.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(summary = "This EndPoint gets all registered payment methods for the user, it also has a paging function that only displays depending on the page and listing size.", description = "you must send the page and corresponding size to list the roles")
    @GetMapping(path = "/{id}")
    public ResponseEntity<PaymentResponse> getById(
            @PathVariable String id) {
        return ResponseEntity.ok(this.paymentService.getById(id));
    }

    @Operation(summary = "This EndPoint insert a payment method depending his requirements and params.", description = "Just have send all the params required.")
    @PostMapping
    public ResponseEntity<PaymentResponse> insert(@RequestBody PaymentRequest payment) {
        System.out.println(payment);
        return ResponseEntity.ok(this.paymentService.create(payment));
    }

    @ApiResponse(responseCode = "400", description = "When the ID is wrong.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(summary = "This EndPoint delete a paymentMethod depending his ID", description = "Just have send an ID, and if that ID is same as method`s ID, It will be eliminated.")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiResponse(responseCode = "400", description = "When the ID is wrong.", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(summary = "This EndPoint update a payment method depending his ID", description = "Just have send an ID, and if that ID is same as method`s ID, It will be update, but you have to send the news params.")
    @PutMapping(path = "/{id}")
    public ResponseEntity<PaymentResponse> update(
            @PathVariable String id, // id por url
            @Validated @RequestBody PaymentRequest payment) {
        return ResponseEntity.ok(this.paymentService.update(id, payment));

    }
}
