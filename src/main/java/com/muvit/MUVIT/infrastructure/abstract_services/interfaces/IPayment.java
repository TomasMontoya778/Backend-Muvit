package com.muvit.MUVIT.infrastructure.abstract_services.interfaces;

import com.muvit.MUVIT.api.dto.request.PaymentRequest;
import com.muvit.MUVIT.api.dto.response.PaymentResponse;

public interface IPayment extends CrudService<PaymentRequest, PaymentResponse, String> {
    public PaymentResponse getById(String id);
}
