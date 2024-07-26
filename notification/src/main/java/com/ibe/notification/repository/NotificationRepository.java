package com.ibe.notification.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ibe.notification.model.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String>{
    
}
