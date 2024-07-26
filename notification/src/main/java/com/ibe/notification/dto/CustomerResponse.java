package com.ibe.notification.dto;

public record CustomerResponse(
    String id,
    String firstname,
    String lastname,
    String email
) {
    
}
