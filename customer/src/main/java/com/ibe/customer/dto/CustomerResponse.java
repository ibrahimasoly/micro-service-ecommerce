package com.ibe.customer.dto;

import com.ibe.customer.models.Adresse;

public record CustomerResponse( 

     String id,
     String firstname,
     String lastname,
     String email,
     Adresse adresse
     ) {
    
}
