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
public class RegisterUserReq extends RegisterReq{
@Size(min = 0, max = 40, message = "The name exceeds the numbers of characters")
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Lastname is required")
    @Size(min = 0, max = 20, message = "The surname exceeds the number of characters")
    private String lastName;
    @NotBlank(message = "Email's required")
    @Email
    @Size(min = 0, max = 40, message = "The email exceeds the number of characters")
    private String email;
    @Size(min = 0, max = 10, message = "The phone number exceeds the numbers of characters")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
}
