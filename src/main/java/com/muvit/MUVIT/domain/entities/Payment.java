package com.muvit.MUVIT.domain.entities;

import com.muvit.MUVIT.util.enums.BankEnum;

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

@Entity(name = "payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 30)
    private String name;
    @Column(length = 16, nullable = false)
    private String number;
    @Column(length = 5, nullable = false)
    private String expirationDate;
    @Column(length = 3, nullable = false)
    private String cvv;
    @Column(nullable = false)
    private BankEnum bank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private User idUser;

}
