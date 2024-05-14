package com.muvit.MUVIT.domain.entities;

import com.muvit.MUVIT.util.enums.RolEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_rol;
    @Column(nullable = false)
    private String nameUser;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private RolEnum rolEnum;
}
