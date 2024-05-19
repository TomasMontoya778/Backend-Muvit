package com.muvit.MUVIT.api.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import com.muvit.MUVIT.util.enums.ServicesEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceRequest {
    // @NotNull(message = "Type of service is required")
    private ServicesEnum typeService;
    @NotBlank(message = "Distance is required")
    private String distance;
    @NotNull(message = "Quantity of assistants are required")
    private int assistant;
    @NotNull(message = "Price of the service is required")
    private Double price;
    // @NotBlank(message = "Start point is required")
    private String startPoint;
    // @NotBlank(message = "Final point is required")
    private String finalPoint;
    @NotNull(message = "The Date is required")
    private LocalDate date;
    @NotNull(message = "The time is required")
    private LocalTime time;
    // @NotNull(message = "Status of the service is required")
    private String statusService;
    @NotBlank(message = "The user id is required")
    private String user;
    private String driver;
    private String paymentMethod;

}
