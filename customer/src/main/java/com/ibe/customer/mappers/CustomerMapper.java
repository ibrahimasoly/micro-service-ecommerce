package com.ibe.customer.mappers;

import org.springframework.stereotype.Component;

import com.ibe.customer.dto.CustomerRequest;
import com.ibe.customer.dto.CustomerResponse;
import com.ibe.customer.models.Customers;

@Component
public class CustomerMapper {

    public Customers toCustomers(CustomerRequest customerRequest){
        return Customers.builder()
        .id(customerRequest.id())
        .firstname(customerRequest.firstname())
        .lastname(customerRequest.lastname())
        .email(customerRequest.email())
        .adresse(customerRequest.adresse())
        .build();
    }

    public CustomerResponse fromCustomer(Customers customers){
        return new CustomerResponse(
        customers.getId(),
        customers.getFirstname(),
        customers.getLastname(), 
        customers.getEmail(), 
        customers.getAdresse());
    }


}
