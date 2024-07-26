package com.ibe.order.kafka;

import java.math.BigDecimal;
import java.util.List;

import com.ibe.order.dto.CustomerResponse;
import com.ibe.order.dto.PurchaseProductResponse;
import com.ibe.order.model.PaymentMetthode;

public record OrderConfirmation(
    String referance,
    BigDecimal toralAmount,
    PaymentMetthode paymentMetthode,
    CustomerResponse customer,
    List<PurchaseProductResponse> products
) {
    
}
