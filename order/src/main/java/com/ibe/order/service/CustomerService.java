package com.ibe.order.service;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ibe.order.dto.CustomerResponse;

@FeignClient(
    name = "customer-service",
    url = "${application.config.customer-url}"
)
public interface CustomerService {
    @GetMapping("/{customer-id}")
    Optional<CustomerResponse> findCustomersById(@PathVariable("customer-id") String id);
    
}
