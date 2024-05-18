package com.muvit.MUVIT.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class RegisterDriverReq extends RegisterReq{
    @Size(min = 0, max = 40, message = "Driver's name exceeds the number of characters allowed")
    @NotBlank(message = "Driver's name is required")
    private String name;
    @Size(min = 0, max = 40, message = "Driver's lastname exceeds the number of characters allowed")
    @NotBlank(message = "Driver's lastname is required")
    private String lastName;
    @NotBlank(message = "Driver's DNI type is required")
    private String DNI_type;
    @NotBlank(message = "Driver's DNI is required")
    private String DNI;
    @NotBlank(message = "Driver's telephone number is required")
    private String phoneNumber;
    @Email
    @NotBlank(message = "Driver's email is required")
    private String email;
}
