package com.ibe.products.dto;

import java.util.List;

import com.ibe.products.models.Products;


public record CategoryResponse(
    Integer id,
    String name,
    String description,
    List<Products> product
) {
    
}
