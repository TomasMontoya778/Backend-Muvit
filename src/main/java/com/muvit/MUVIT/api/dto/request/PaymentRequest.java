package com.muvit.MUVIT.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private String name;
    @NotBlank(message = "Card number is required")
    private String number;
    @NotBlank(message = "Card expiration date is required")
    private String expirationDate;
    @NotBlank(message = "Card cvv is required")
    private String cvv;
    private String bank;
    @NotBlank(message = "User id is required")
    private String idUser;
}
