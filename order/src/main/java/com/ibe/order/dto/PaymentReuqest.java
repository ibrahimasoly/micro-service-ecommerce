package com.ibe.order.dto;

import java.math.BigDecimal;

import com.ibe.order.model.PaymentMetthode;

public record PaymentReuqest(
    String OderReference,
    BigDecimal amount,
    PaymentMetthode paymentMethode,
    Integer orderId,
    CustomerResponse customer
) {
    
}
