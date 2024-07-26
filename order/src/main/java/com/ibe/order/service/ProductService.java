package com.ibe.order.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.ibe.order.dto.PurchaseProductRequest;
import com.ibe.order.dto.PurchaseProductResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(
    name = "product-service",
    url = "${application.config.product-url}"
)
public interface ProductService {

    @PostMapping("/purchase")
    List<PurchaseProductResponse> purchateProducts(@RequestBody List<PurchaseProductRequest> requests);
    
}
