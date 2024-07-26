package com.ibe.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ibe.order.dto.PaymentReuqest;

@FeignClient(
    name = "payment-service", 
    url = "${application.config.payment-url}"
    )
public interface PaymentService {

    @PostMapping
    Integer createPayment(@RequestBody PaymentReuqest reuqest);
    
}
