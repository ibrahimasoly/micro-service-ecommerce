package com.ibe.payment.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


//c'est obligatoire de mettre l'annotation @validated il va s'auto declencher en faisant a ces methode @NotNull etc..
@Validated
public record Customer(
    String id,
    @NotNull(message = "Firstname is required")
    String firstname,
    @NotNull(message = "Lastname is required")
    String lastname,
    @NotNull(message = "Email is required")
    @Email(message = "The customer email is not correctly formatted")
    String email
) {
    
}
