package com.ibe.products.mapper;

import org.springframework.stereotype.Component;

import com.ibe.products.dto.ProductRequest;
import com.ibe.products.dto.ProductResponse;
import com.ibe.products.dto.PurchateProductResponse;
import com.ibe.products.models.Categorie;
import com.ibe.products.models.Products;

@Component
public class ProductMapper {
    public Products toProducts(ProductRequest productRequest){

        return Products.builder()
        .id(productRequest.id())
        .name(productRequest.name())
        .description(productRequest.description())
        .availableQuantity(productRequest.availableQuantity())
        .price(productRequest.price())
        .category(Categorie.builder().id(productRequest.categoryId()).build())
        .build();
    }

    public ProductResponse fromProduct(Products products){
        return new ProductResponse(
        products.getId(),
        products.getName(),
        products.getDescription(), 
        products.getAvailableQuantity(), 
        products.getPrice(), 
        products.getCategory().getId(), 
        products.getCategory().getName(), 
        products.getCategory().getDescription());
    }

    public PurchateProductResponse toPurchateProductResponse(Products product, double quantity){
        return new PurchateProductResponse(
        product.getId(), 
        product.getName(), 
        product.getDescription(), 
        product.getPrice(), 
        quantity
        );
    }
}
