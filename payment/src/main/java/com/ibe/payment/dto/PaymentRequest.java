package com.ibe.payment.dto;

import java.math.BigDecimal;

import com.ibe.payment.model.PaymentMethode;


public record PaymentRequest(
    Integer id,
    String OderReference,
    BigDecimal amount,
    PaymentMethode paymentMethode,
    Integer orderId,
    Customer customer

) {
    
}
