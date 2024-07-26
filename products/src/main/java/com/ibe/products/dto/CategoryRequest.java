package com.ibe.products.dto;

import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
    Integer id,
    @NotNull(message = "Category name is required")
    String name,
    @NotNull(message = "Category name is required")
    String description
) {
    
}
