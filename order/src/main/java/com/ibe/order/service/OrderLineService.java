package com.ibe.order.service;

import org.springframework.stereotype.Service;

import com.ibe.order.dto.OrderLineRequest;
import com.ibe.order.mapper.OrderLineMapper;
import com.ibe.order.repository.OrderLineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public Integer createOrderLine(OrderLineRequest request){

        return orderLineRepository.save(mapper.toOrderLine(request)).getId();
        
    }
    
}
