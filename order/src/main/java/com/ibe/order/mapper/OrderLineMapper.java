package com.ibe.order.mapper;

import org.springframework.stereotype.Component;

import com.ibe.order.dto.OrderLineRequest;
import com.ibe.order.model.Order;
import com.ibe.order.model.OrderLine;

@Component
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request){
        return OrderLine.builder()
            .id(request.id())
            .order(Order.builder().id(request.orderId()).build())
            .productId(request.productId())
            .quantity(request.quantity())
            .build();
    }
    
}
