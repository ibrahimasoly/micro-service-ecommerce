package com.ibe.order.mapper;

import org.springframework.stereotype.Component;

import com.ibe.order.dto.OrderRequest;
import com.ibe.order.dto.OrderResponse;
import com.ibe.order.model.Order;

@Component
public class OrderMapper {

    public Order toOrder(OrderRequest request){
        if(request==null){
            return null;
        }
        return Order.builder()
            .id(request.id())
            .reference(request.reference())
            .totalAmount(request.amount())
            .paymentMetthode(request.paymentMetthode())
            .customerId(request.customerId())
            .build();
    }

    public OrderResponse fromOrder(Order order){
        return new OrderResponse(
            order.getId(), 
            order.getReference(), 
            order.getTotalAmount(), 
            order.getPaymentMetthode(), 
            order.getCustomerId()
            );
    }
    
}
