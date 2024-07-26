package com.ibe.order.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ibe.order.model.PaymentMetthode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@JsonInclude(Include.NON_EMPTY)
public record OrderRequest(
    Integer id,
    String reference,
    @Positive(message = "totalAmount shoul be positif")
    BigDecimal amount,
    @NotNull(message = "paymentMetthode shoul be precised")
    PaymentMetthode paymentMetthode,
    @NotNull(message = "customerId shoul be not null")
    @NotEmpty(message = "customerId shoul be not null")
    @NotBlank(message = "customerId shoul be not null")
    String customerId,
    @NotEmpty(message = "You should at least purchase one product")
    List<PurchaseProductRequest> product
) {
    
}
