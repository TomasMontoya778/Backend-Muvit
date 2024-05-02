package com.muvit.MUVIT.domain.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.muvit.MUVIT.util.enums.RolEnum;
import com.muvit.MUVIT.util.enums.StateServiceEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Service")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_service;
    @Column(nullable = false)
    private RolEnum typeService;
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
    private StateServiceEnum statusService;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private LocalTime time;

    /*Foreign Keys */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User id_user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_driver", referencedColumnName = "id_driver")
    private Driver id_driver;
}
