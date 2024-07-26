package com.ibe.products.dto;

import java.math.BigDecimal;

public record PurchateProductResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
    
}
