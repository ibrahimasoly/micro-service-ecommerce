package com.ibe.products.service;



import org.springframework.stereotype.Service;

import com.ibe.products.dto.CategoryRequest;
import com.ibe.products.dto.CategoryResponse;
import com.ibe.products.mapper.CategoryMapper;
import com.ibe.products.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public void creatCategory(CategoryRequest request){
        repository.save(mapper.toCategorie(request));
    }

    public CategoryResponse findCategoryById(Integer id){
        return repository.findById(id)
        .map(mapper::fromCategorie)
        .orElseThrow(()-> new EntityNotFoundException("product provide with by Id"+id));
    }
    
}
