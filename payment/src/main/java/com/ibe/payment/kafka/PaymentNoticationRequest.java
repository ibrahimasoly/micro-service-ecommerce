package com.ibe.payment.kafka;

import java.math.BigDecimal;

import com.ibe.payment.model.PaymentMethode;


public record PaymentNoticationRequest(
    String orderReference,
    BigDecimal amount,
    PaymentMethode paymentMethode,
    String customerFirstName,
    String customerLastName,
    String cutomerEmail

) {

    
}
