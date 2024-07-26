package com.ibe.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.ibe.order.model.OrderLine;


@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer>{
    
}
