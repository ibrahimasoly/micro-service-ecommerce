package com.ibe.notification.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
    
}
