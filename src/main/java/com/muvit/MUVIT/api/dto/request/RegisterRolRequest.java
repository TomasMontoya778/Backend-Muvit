package com.muvit.MUVIT.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRolRequest {
 @NotBlank(message = "User name is required")
     private String nameUser;
     @NotBlank(message = "User password is required")
     private String password;
     @NotBlank(message = "User rol is required")
     private String rolEnum;
     private String userPhoto;
}
