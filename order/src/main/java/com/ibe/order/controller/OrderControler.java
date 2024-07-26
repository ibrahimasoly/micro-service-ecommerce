package com.ibe.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibe.order.dto.OrderRequest;
import com.ibe.order.dto.OrderResponse;
import com.ibe.order.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderControler {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest request) {
        
        return ResponseEntity.ok(service.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getMethodName() {
        return ResponseEntity.ok(service.allOrder());
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<OrderResponse> getMethodName(@PathVariable("order_id") Integer id) {
        return ResponseEntity.ok(service.findOrderById(id));
    }
    
    
    
    
}
