package com.ibe.products.controler;

import org.springframework.web.bind.annotation.RestController;

import com.ibe.products.dto.ProductRequest;
import com.ibe.products.dto.ProductResponse;
import com.ibe.products.dto.PurchaseProductRequest;
import com.ibe.products.dto.PurchateProductResponse;
import com.ibe.products.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductControler {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<Integer> creatProduct(@RequestBody @Valid ProductRequest request) {
        
        return ResponseEntity.ok(service.creatProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<PurchateProductResponse>> postMethodName(@RequestBody @Valid List<PurchaseProductRequest> request) {
        
        return ResponseEntity.ok(service.purchateProducts(request));
    }
    

    @GetMapping
    public ResponseEntity<List<ProductResponse>> allProduct() {
        return ResponseEntity.ok(service.allProduct());
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable("product_id") Integer id) {
        return ResponseEntity.ok(service.findProductById(id));
    }
    
    
    
}
