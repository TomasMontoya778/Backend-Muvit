package com.muvit.MUVIT.domain.entities;

import java.time.LocalDate;

import com.muvit.MUVIT.util.enums.BodyEnum;
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

@Entity(name = "truck")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Truck {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private BodyEnum body;
  @Column(length = 20, nullable = false)
  private String model;
  @Column(nullable = false)
  private LocalDate soat;
  @Column(nullable = false)
  private LocalDate tecnomecanica;
  @Enumerated(EnumType.STRING)
  private StateServiceEnum status;
  @Column(nullable = false)
  private String licensePlate;

  /* Foreign Key */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_driver_truck", referencedColumnName = "id_driver")
  private Driver id_driver_truck;
}
