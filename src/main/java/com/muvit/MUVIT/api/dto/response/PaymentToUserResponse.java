package com.muvit.MUVIT.api.dto.response;

import com.muvit.MUVIT.util.enums.BankEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentToUserResponse {
    private String id;
    private String name;
    private String number;
    private String expirationDate;
    private String cvv;
    private BankEnum bank;
}
