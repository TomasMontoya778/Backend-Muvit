package com.muvit.MUVIT.domain.entities;


import com.muvit.MUVIT.util.enums.Dni_type_Enum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "driver")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_driver;
    @Column(nullable = false, length = 40)
    private String name;
    @Column(nullable = false, length = 40)
    private String lastName;
    @Column(nullable = false)
    private Dni_type_Enum DNI_type;
    @Column(nullable = false)
    private String DNI;
    @Column(nullable = false, length = 10)
    private Long phoneNumber;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Rol rol;
}
