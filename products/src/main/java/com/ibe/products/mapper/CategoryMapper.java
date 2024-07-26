package com.ibe.products.mapper;

import org.springframework.stereotype.Component;

import com.ibe.products.dto.CategoryRequest;
import com.ibe.products.dto.CategoryResponse;
import com.ibe.products.models.Categorie;

@Component
public class CategoryMapper {
    
    public Categorie toCategorie(CategoryRequest categoryRequest){
        return Categorie.builder()
            .id(categoryRequest.id())
            .name(categoryRequest.name())
            .description(categoryRequest.description())
            .build();
    }

    public CategoryResponse fromCategorie(Categorie categorie){
        return new CategoryResponse(
        categorie.getId(),
        categorie.getName(), 
        categorie.getDescription(), 
        categorie.getProducts()
        );
    }
}
