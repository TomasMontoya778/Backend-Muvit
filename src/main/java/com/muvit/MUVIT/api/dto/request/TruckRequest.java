package com.muvit.MUVIT.api.dto.request;
import com.muvit.MUVIT.util.enums.BodyEnum;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TruckRequest {
     @NotBlank(message = "Model is required")
     @Size(min = 0, max = 100)
     private String model;
     @NotNull(message = "SOAT expiry is required")
     private LocalDate soat;
     @NotNull(message = "tecnomecanica expiry is required")
     private LocalDate tecnomecanica;
     @NotNull(message = "The truck's Size is required")
     private BodyEnum body;
     private String status;
     private String id_driver;
}
