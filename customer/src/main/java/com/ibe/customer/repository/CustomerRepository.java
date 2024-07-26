package com.ibe.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibe.customer.models.Customers;

@Repository
public interface CustomerRepository extends MongoRepository<Customers, String>{

    
}
