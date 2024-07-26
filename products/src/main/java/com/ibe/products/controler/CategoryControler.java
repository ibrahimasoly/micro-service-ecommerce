package com.ibe.products.controler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibe.products.dto.CategoryRequest;
import com.ibe.products.service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryControler {

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<Void> creatCategory(@RequestBody @Valid CategoryRequest request){
        service.creatCategory(request);
        return ResponseEntity.accepted().build();
    }
    
}
