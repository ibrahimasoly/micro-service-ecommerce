package com.ibe.customer.exception;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class CustomerNotFoundException extends RuntimeException {
    private String msg;
}
