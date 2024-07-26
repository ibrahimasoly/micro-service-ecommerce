package com.ibe.payment.mapper;

import org.springframework.stereotype.Component;

import com.ibe.payment.dto.PaymentRequest;
import com.ibe.payment.model.Payment;

@Component
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request){
        if(request==null){
            return null;
        }
        return Payment.builder()
                .id(request.id())
                .amount(request.amount())
                .paymentMethode(request.paymentMethode())
                .orderId(request.orderId())
                .build();
    }
    
}
