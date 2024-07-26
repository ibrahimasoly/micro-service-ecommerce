package com.ibe.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibe.order.model.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
    
}
