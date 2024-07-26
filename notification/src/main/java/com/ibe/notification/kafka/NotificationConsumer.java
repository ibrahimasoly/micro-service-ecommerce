package com.ibe.notification.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ibe.notification.email.EmailService;
import com.ibe.notification.mapper.NotifcationMapper;
import com.ibe.notification.repository.NotificationRepository;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;
    private final NotifcationMapper mapper;

    @KafkaListener(topics = "order-topic")
    public void orderConsumer(OrderConfirmation orderConfirmation) throws MessagingException{
        log.info(String.format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        repository.save(mapper.fromOrderConfirmation(orderConfirmation));

        var customerName = orderConfirmation.customer().lastname()+" "+orderConfirmation.customer().firstname();

        emailService.sendOrderComfirmationEmail(
            orderConfirmation.customer().email(), 
            customerName, 
            orderConfirmation.toralAmount(), 
            orderConfirmation.referance(), 
            orderConfirmation.products()
        );
    }


    @KafkaListener(topics = "payment-topic")
    public void paymentConsumer(PaymentNoticationRequest paymentConsumer) throws MessagingException{
        log.info(String.format("Consuming the message from payment-topic Topic:: %s", paymentConsumer));
        repository.save(mapper.fromPaymentConfirmation(paymentConsumer));

        var customerName = paymentConsumer.customerLastName()+" "+paymentConsumer.customerFirstName();

       emailService.sendPaymentSuccesEmail(
        paymentConsumer.cutomerEmail(), 
        customerName, 
        paymentConsumer.amount(), 
        paymentConsumer.orderReference()
       );
    }
    
}
