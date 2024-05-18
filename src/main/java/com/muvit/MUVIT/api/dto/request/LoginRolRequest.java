package com.muvit.MUVIT.api.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRolRequest {
     @NotBlank(message = "User name is required")
     private String nameUser;
     @NotBlank(message = "User password is required")
     private String password;

}
