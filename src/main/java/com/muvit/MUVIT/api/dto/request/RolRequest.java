package com.muvit.MUVIT.api.dto.request;

import com.muvit.MUVIT.util.enums.RolEnum;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolRequest {
     @NotBlank(message = "User name is required")
     @NotBlank(message = "User password is required")
     private String nameUser;
     private String password;
     @NotBlank(message = "User rol is required")
     private RolEnum rol;
}
