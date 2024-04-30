package com.muvit.MUVIT.domain.entities;


import java.time.LocalDate;

import com.muvit.MUVIT.util.enums.BodyEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Truck {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Column(nullable = false)
  private BodyEnum body;
  @Column(length = 20, nullable = false)
  private String model;
  @Column(nullable = false)
  private LocalDate soat;
  @Column(nullable = false)
  private LocalDate tecnomecanica;
  private Driver id_driver;

}
