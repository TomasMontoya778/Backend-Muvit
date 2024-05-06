package com.muvit.MUVIT.api.dto.request;

import com.muvit.MUVIT.util.enums.RolEnum;
import com.muvit.MUVIT.util.enums.StateServiceEnum;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceRequest {
    @NotBlank(message = "Type of service is required")
    private RolEnum typeService;
    @NotBlank(message = "Distance is required")
    private String distance;
    @NotBlank(message = "Quantity of assistants are required")
    private int assistant;
    @NotBlank(message = "Price of the service is required")
    private Double price;
    @NotBlank(message = "Start point is required")
    private String startPoint;
    @NotBlank(message = "Final point is required")
    private String finalPoint;
    @NotBlank(message = "Status of the service is required")
    private StateServiceEnum statusService;
}
