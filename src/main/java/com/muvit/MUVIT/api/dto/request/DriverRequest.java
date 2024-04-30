package com.muvit.MUVIT.api.dto.request;

import com.muvit.MUVIT.domain.entities.Rol;
import com.muvit.MUVIT.util.enums.Dni_type_Enum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverRequest {

    private String name;
    private String lastName;
    private Dni_type_Enum DNI_type;
    private String DNI;
    private Long phoneNumber;
    private String email;
    private Rol rol;

}
