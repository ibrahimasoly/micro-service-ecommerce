package com.ibe.notification.kafka;

import java.math.BigDecimal;

import com.ibe.notification.model.PaymentMetthode;

public record PaymentNoticationRequest(
    String orderReference,
    BigDecimal amount,
    PaymentMetthode paymentMethode,
    String customerFirstName,
    String customerLastName,
    String cutomerEmail
) {
    
}
