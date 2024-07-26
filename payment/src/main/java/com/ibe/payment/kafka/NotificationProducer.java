package com.ibe.payment.kafka;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    private final KafkaTemplate <String,PaymentNoticationRequest> kafkaTemplate;

    public void sendNoticationPayment(PaymentNoticationRequest noticationRequest){
        log.info("Sending notification with body = < {} > ",  noticationRequest);
        Message<PaymentNoticationRequest> message = MessageBuilder
            .withPayload(noticationRequest)
            .setHeader(TOPIC, "payment-topic")
            .build();

        kafkaTemplate.send(message);

    }
    
}
