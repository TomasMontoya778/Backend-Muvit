package com.muvit.MUVIT.domain.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.muvit.MUVIT.util.enums.PaymentMethods;
import com.muvit.MUVIT.util.enums.ServicesEnum;
import com.muvit.MUVIT.util.enums.StateServiceEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "service")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_service;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ServicesEnum typeService;
    @Column(nullable = false)
    private String distance;
    @Column(nullable = true)
    private int assistant;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String startPoint;
    @Column(nullable = false)
    private String finalPoint;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StateServiceEnum statusService;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private LocalTime time;
    @Column(nullable = false)
    private PaymentMethods payment;

    /* Foreign Keys */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User id_user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_driver", referencedColumnName = "id_driver")
    private Driver id_driver;
}
