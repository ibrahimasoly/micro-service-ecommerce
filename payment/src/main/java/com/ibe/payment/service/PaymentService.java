package com.ibe.payment.service;


import org.springframework.stereotype.Service;

import com.ibe.payment.dto.PaymentRequest;
import com.ibe.payment.kafka.NotificationProducer;
import com.ibe.payment.kafka.PaymentNoticationRequest;
import com.ibe.payment.mapper.PaymentMapper;
import com.ibe.payment.model.Payment;
import com.ibe.payment.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;


    public Integer createPayment(PaymentRequest request){
        Payment payment = repository.save(mapper.toPayment(request));

        this.notificationProducer.sendNoticationPayment(
            new PaymentNoticationRequest(
                request.OderReference(), 
                request.amount(), 
                request.paymentMethode(), 
                request.customer().firstname(), 
                request.customer().lastname(), 
                request.customer().email())
        );

        return payment.getId();
    }


    
}
