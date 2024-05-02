package com.muvit.MUVIT.domain.entities;

import java.util.ArrayList;
import java.util.List;

import com.muvit.MUVIT.util.enums.RolEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "Rol")
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
    private RolEnum rol;

    /* Foreign Keys */
    @OneToMany(mappedBy = "id_user_rol", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<User> id_user_rol = new ArrayList<>();

    @OneToMany(mappedBy = "id_driver_rol", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Driver> id_driver_rol = new ArrayList<>();

    @OneToMany(mappedBy = "id_admin_rol", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Admin> id_admin_rol = new ArrayList<>();
}
