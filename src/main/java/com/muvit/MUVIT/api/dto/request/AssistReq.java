package com.muvit.MUVIT.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssistReq {
@Size(min = 0, max = 40, message = "Assistant name exceeds the number of characters allowed")
    @NotBlank(message = "Assistant name is required")
    private String name;
    @Size(min = 0, max = 40, message = "Assistant lastname exceeds the number of characters allowed")
    @NotBlank(message = "Assistant lastname is required")
    private String lastName;
    @NotBlank(message = "Assistant DNI type is required")
    private String DNI_type;
    @Size(min = 0, max = 40, message = "Assistant DNI exceeds the number of characters allowed")
    @NotBlank(message = "Assistant DNI is required")
    private String DNI;
    @NotBlank(message = "ID driver is required")
    private String driver;
}
