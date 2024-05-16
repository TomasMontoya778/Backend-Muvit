package com.muvit.MUVIT.api.dto.response;

import java.time.LocalDate;

import com.muvit.MUVIT.util.enums.BodyEnum;
import com.muvit.MUVIT.util.enums.StateServiceEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TruckResponse {
    private String id;
    private BodyEnum body;
    private String model;
    private LocalDate soat;
    private LocalDate tecnomecanica;
    private StateServiceEnum status;
    private DriverToTruckResponse id_driver;
}
