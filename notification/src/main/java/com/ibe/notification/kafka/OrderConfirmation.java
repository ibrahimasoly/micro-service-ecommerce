package com.ibe.notification.kafka;

import java.math.BigDecimal;
import java.util.List;

import com.ibe.notification.dto.CustomerResponse;
import com.ibe.notification.dto.ProductResponse;
import com.ibe.notification.model.PaymentMetthode;

public record OrderConfirmation(
    String referance,
    BigDecimal toralAmount,
    PaymentMetthode paymentMetthode,
    CustomerResponse customer,
    List<ProductResponse> products
) {
    
}
