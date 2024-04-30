package com.muvit.MUVIT.api.dto.request;
import com.muvit.MUVIT.util.enums.BodyEnum;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TruckRequest {
private BodyEnum body;
private String model;
private LocalDate soat;
private LocalDate tecnomecanica;
}
