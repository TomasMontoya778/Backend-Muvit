package com.muvit.MUVIT.api.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import com.muvit.MUVIT.util.enums.BodyEnum;
import com.muvit.MUVIT.util.enums.PaymentMethods;
import com.muvit.MUVIT.util.enums.ServicesEnum;
import com.muvit.MUVIT.util.enums.StateServiceEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceToUserResponse {
    private String id_service;
    private ServicesEnum typeService;
    private String distance;
    private int assistant;
    private Double price;
    private String startPoint;
    private String finalPoint;
    private LocalDate date;
    private LocalTime time;
    private StateServiceEnum statusService;
    private DriverResponse driver;
    private PaymentMethods paymentMethod;
    private BodyEnum size;

}
