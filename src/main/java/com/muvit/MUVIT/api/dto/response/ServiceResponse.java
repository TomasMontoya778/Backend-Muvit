package com.muvit.MUVIT.api.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class ServiceResponse {
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
    private UserToServiceResponse user;
    private DriverBasicResponse driver;
    private PaymentMethods paymentMethod;
}
