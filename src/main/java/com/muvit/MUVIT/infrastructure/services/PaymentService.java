package com.muvit.MUVIT.infrastructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.muvit.MUVIT.api.dto.request.PaymentRequest;
import com.muvit.MUVIT.api.dto.response.PaymentResponse;
import com.muvit.MUVIT.api.dto.response.UserToPaymentResponse;
import com.muvit.MUVIT.domain.entities.Payment;
import com.muvit.MUVIT.domain.entities.ServiceEntity;
import com.muvit.MUVIT.domain.entities.User;
import com.muvit.MUVIT.domain.repositories.PaymentRepository;
import com.muvit.MUVIT.domain.repositories.UserRepository;
import com.muvit.MUVIT.infrastructure.abstract_services.interfaces.IPayment;
import com.muvit.MUVIT.util.enums.BankEnum;
import com.muvit.MUVIT.util.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentService implements IPayment {
    @Autowired
    private final PaymentRepository paymentRepository;
    @Autowired
    private final UserRepository userRepository;

    @Override
    public void delete(String id) {
        Payment payment = this.find(id);
        this.paymentRepository.delete(payment);
    }

    @Override
    public PaymentResponse create(PaymentRequest request) {
        System.out.println("create" + request);
        Payment payment = this.requestToEntity(request, new Payment());
        return this.entityToResponse(this.paymentRepository.save(payment));
    }

    @Override
    public PaymentResponse update(String id, PaymentRequest request) {
        Payment payment = find(id);
        Payment UpdatePayment = this.requestToEntity(request, payment);
        return this.entityToResponse(this.paymentRepository.save(UpdatePayment));
    }

    @Override
    public Page<PaymentResponse> getAll(int page, int size) {
        if (page < 0)
            page = 0;
        PageRequest pagination = PageRequest.of(page, size);
        return this.paymentRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public PaymentResponse getById(String id) {
        return this.entityToResponse(this.find(id));
    }

    private Payment requestToEntity(PaymentRequest paymentRequest, Payment payment) {
        BeanUtils.copyProperties(paymentRequest, payment);
        String number = paymentRequest.getNumber();
        String firstChar = number.substring(0, 1);
        if (paymentRequest.getName() == null) {
            switch (firstChar) {
                case "3":
                    payment.setName("AMERICANEXPRESS");
                    break;
                case "4":
                    payment.setName("VISA");
                    break;
                case "5":
                    payment.setName("MASTERCARD");
                    break;
                default:

            }
        }
        if(payment.getBank()==null){
            switch (firstChar) {
                case "3":
                    payment.setBank(BankEnum.AMERICANEXPRESS);
                    break;
                case "4":
                    payment.setBank(BankEnum.VISA);
                    break;
                case "5":
                    payment.setBank(BankEnum.MASTERCARD);
                    break;
                default:
            }
        }
        payment.setIdUser(this.findUser(paymentRequest.getIdUser()));  
        return payment;
    }

    private PaymentResponse entityToResponse(Payment payment) {
        UserToPaymentResponse userResponse = this.userToPaymentResponse(payment.getIdUser());
        PaymentResponse paymentResponse = new PaymentResponse();
        BeanUtils.copyProperties(payment, paymentResponse);
        paymentResponse.setUser(userResponse);
        return paymentResponse;
    }

    private UserToPaymentResponse userToPaymentResponse(User user) {
        UserToPaymentResponse userResponse = new UserToPaymentResponse();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;
    }

    private Payment find(String id) {
        return this.paymentRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("No se encontró el ID del servicio"));
    }

    private User findUser(String id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("No se encontró el ID del servicio"));
    }

}
