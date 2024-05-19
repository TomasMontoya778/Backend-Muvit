package com.muvit.MUVIT.api.dto.response;

import com.muvit.MUVIT.util.enums.RolEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolResponse {
    private long id_rol;
    private String nameUser;
    private String password;
    private RolEnum rol;
}
