package com.muvit.MUVIT.api.dto.response;

import com.muvit.MUVIT.domain.entities.Rol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminResponse {
    private String id_admin;
    private Rol rol;
}
