package com.muvit.MUVIT.api.dto.response;

import com.muvit.MUVIT.util.enums.BankEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private String id;
    private String name;
    private String number;
    private String expirationDate;
    private String cvv;
    private BankEnum bank;
    private UserToPaymentResponse user;
}
