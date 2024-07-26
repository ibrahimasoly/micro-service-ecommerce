package com.ibe.customer.services;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ibe.customer.dto.CustomerRequest;
import com.ibe.customer.dto.CustomerResponse;
import com.ibe.customer.exception.CustomerNotFoundException;
import com.ibe.customer.mappers.CustomerMapper;
import com.ibe.customer.models.Customers;
import com.ibe.customer.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest customerRequest){
        Customers customers =customerRepository.save(customerMapper.toCustomers(customerRequest));
        return customers.getId();
    }

    public void updateCustomer(CustomerRequest request){
        Customers customers = customerRepository.findById(request.id()).orElseThrow(()-> new CustomerNotFoundException(
            String.format("Cannot update customer:: No customer found with the provided ID: %s", request.id())
        )) ;

        mergCustomers(customers, request);

        customerRepository.save(customers);

    }

    public void mergCustomers(Customers customers, CustomerRequest request){
        if(StringUtils.isNotBlank(request.firstname())){
            customers.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())){
            customers.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email())){
            customers.setEmail(request.email());
        }
        if(request.adresse()!= null){
            customers.setAdresse(request.adresse());
        }
    }

    public List<CustomerResponse> allcustomer(){
        return this.customerRepository.findAll()
        .stream()
        .map(this.customerMapper::fromCustomer)
        .toList(); 
    }

    public CustomerResponse findCustomerById(String id){
        return customerRepository.findById(id)
        .map(this.customerMapper::fromCustomer)
        .orElseThrow(() -> new CustomerNotFoundException("No customer found with the provide Id : "+id));
    }

    public boolean existCustomerById(String id){
        return customerRepository.findById(id).isPresent();
    }

    public void deletedCustomerById(String id){
        customerRepository.deleteById(id);
    }

}
