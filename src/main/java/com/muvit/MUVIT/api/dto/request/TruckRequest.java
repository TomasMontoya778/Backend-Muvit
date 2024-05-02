package com.muvit.MUVIT.api.dto.request;
import com.muvit.MUVIT.util.enums.BodyEnum;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TruckRequest {
     @NotBlank(message = "Bodytruck is required")
     @NotBlank(message = "Model is required")
     private String model;
     @NotBlank(message = "SOAT expiry is required")
     private LocalDate soat;
     @NotBlank(message = "tecnomecanica expiry is required")
     private LocalDate tecnomecanica;
     private BodyEnum body;
}
