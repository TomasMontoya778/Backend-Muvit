package com.muvit.MUVIT.api.dto.request;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverRequest {
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
    @NotBlank(message = "Driver's email is required")
    private String email;
    private Long rol;

}
