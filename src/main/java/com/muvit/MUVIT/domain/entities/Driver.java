package com.muvit.MUVIT.domain.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private String DNI_type;
    @Column(nullable = false)
    private Long DNI;
    @Column(nullable = false, length = 10)
    private Long phoneNumber;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Rol id_rol;

    /* Foreign Key*/
    @OneToOne(mappedBy = "id_driver", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Service driverService;
}
