package com.muvit.MUVIT.domain.entities;

import com.muvit.MUVIT.util.enums.DNITypeEnum;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "assistant")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Assistant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false, length = 14)
    private String name;
    @Column(nullable = false, length = 14)
    private String lastName;
    @Enumerated(EnumType.STRING)
    private DNITypeEnum DNI_type;
    @Column(nullable = false, unique = true)
    private String DNI;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name =  "id_driver", referencedColumnName = "id_driver")
    private Driver driver;
}
