package com.ibe.order.dto;

import java.math.BigDecimal;

import com.ibe.order.model.PaymentMetthode;

public record OrderResponse(
    Integer id,
    String reference,
    BigDecimal totalAmount,
    PaymentMetthode paymentMetthode,
    String customerId
) {
}
