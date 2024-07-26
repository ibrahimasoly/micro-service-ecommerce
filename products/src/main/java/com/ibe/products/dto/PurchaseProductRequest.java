package com.ibe.products.dto;

import jakarta.validation.constraints.NotNull;

public record PurchaseProductRequest(
    @NotNull(message = "product is mandatory")
    Integer productId,
    @NotNull(message = "Quantity is mandatory")
    double quantity
) {
    
}
