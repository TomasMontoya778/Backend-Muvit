package com.muvit.MUVIT.api.dto.response;

import com.muvit.MUVIT.domain.entities.Rol;
import com.muvit.MUVIT.util.enums.DNITypeEnum;

import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriverResponse {
    private String id_driver;
    private String name;
    private String lastName;
    private DNITypeEnum DNI_type;
    private String DNI;
    private String phoneNumber;
    private String email;
    private RolResponse rol;
}
