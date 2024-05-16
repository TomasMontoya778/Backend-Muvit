package com.muvit.MUVIT.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasicRolResponse {
    private long id_rol;
    private String userPhoto;
}
