package com.ibe.customer.controler;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibe.customer.dto.CustomerRequest;
import com.ibe.customer.dto.CustomerResponse;
import com.ibe.customer.services.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerControler {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest customerRequest){
        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest customerRequest){
        customerService.updateCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> allCustomer() {
        return ResponseEntity.ok(customerService.allcustomer());
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable("customer-id") String id){
        return ResponseEntity.ok(customerService.findCustomerById(id));
    }
    
    @DeleteMapping("{customer-id}")
    public ResponseEntity<Void> deletedCustomerById(@PathVariable("customer-id") String id){
        customerService.deletedCustomerById(id);
        return ResponseEntity.accepted().build();
    }

}
