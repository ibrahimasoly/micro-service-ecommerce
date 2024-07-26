package com.ibe.notification.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.ibe.notification.kafka.OrderConfirmation;
import com.ibe.notification.kafka.PaymentNoticationRequest;
import com.ibe.notification.model.Notification;
import com.ibe.notification.model.NotificationType;

@Component
public class NotifcationMapper {
    
    public Notification fromOrderConfirmation(OrderConfirmation confirmation){
        return Notification.builder()
                .notificationType(NotificationType.ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(confirmation)
                .build();
    }
    public Notification fromPaymentConfirmation(PaymentNoticationRequest confirmation){
        return Notification.builder()
                .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentSucces(confirmation)
                .build();
    }
}
