package com.ibe.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibe.order.dto.CustomerResponse;
import com.ibe.order.dto.OrderLineRequest;
import com.ibe.order.dto.OrderRequest;
import com.ibe.order.dto.OrderResponse;
import com.ibe.order.dto.PaymentReuqest;
import com.ibe.order.dto.PurchaseProductRequest;
import com.ibe.order.exception.BusnessException;
import com.ibe.order.kafka.OrderConfirmation;
import com.ibe.order.kafka.OrderKafkaProducer;
import com.ibe.order.mapper.OrderMapper;

import com.ibe.order.repository.OrderRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final CustomerService customerService;
    private final OrderLineService orderLineService;
    private final ProductService productService;
    private final OrderKafkaProducer orderKafkaProducer;
    private final PaymentService paymentService;


    @Transactional
    public Integer createOrder(OrderRequest request){
        CustomerResponse customer = this.customerService.findCustomersById(request.customerId())
        .orElseThrow(()-> new BusnessException("Cannot create order :: No Customer Exist with the provide ID"));

        var purchaseProductResponse = productService.purchateProducts(request.product());

        var order = orderRepository.save(mapper.toOrder(request));

        for(PurchaseProductRequest purchaseResponse:request.product()){
            orderLineService.createOrderLine(
                new OrderLineRequest(
                    null, 
                    order.getId(), 
                    purchaseResponse.productId(), 
                    purchaseResponse.quantity()
                    )
            );
        }

        paymentService.createPayment( 
            new PaymentReuqest(
                request.reference(), 
                request.amount(), 
                request.paymentMetthode(), 
                order.getId(), 
                customer
                )
        );

        orderKafkaProducer.sendOrderConfirmation(
            new OrderConfirmation(
            request.reference(), 
            request.amount(), 
            request.paymentMetthode(), 
            customer, 
            purchaseProductResponse
            )
        );


        return order.getId();
    }

    public List<OrderResponse> allOrder(){
        
        return orderRepository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .toList();
    }
    public OrderResponse findOrderById(Integer id){
        
        return orderRepository.findById(id)
                .map(mapper::fromOrder)
                .orElseThrow(()-> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }
    
}
