package com.muvit.MUVIT.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 20, nullable = false)
    private String name;
    @Column(length = 20, nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false, length = 10)
    private Long phoneNumber;
     @Column(nullable = false)
    private Rol id_rol;
    
    /* Foreign Key*/
    @OneToOne(mappedBy = "id_user", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Service userService;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_rol", referencedColumnName = "id")
    private Rol fk_id_rol_user;
    
}
