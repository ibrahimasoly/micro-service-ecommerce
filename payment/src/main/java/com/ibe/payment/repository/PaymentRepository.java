package com.ibe.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibe.payment.model.Payment;



@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    
}
