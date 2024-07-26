package com.ibe.products.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ibe.products.exception.PurchaseProductException;

import jakarta.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(PurchaseProductException.class)
    public ResponseEntity<String> handlerPuchaseProductException(PurchaseProductException exp){
        return ResponseEntity
            .status(BAD_REQUEST)
            .body(exp.getMessage());
    }

    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handlerEntityNotFoundException(EntityNotFoundException exp){
        return ResponseEntity
            .status(BAD_REQUEST)
            .body(exp.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handlerValidationException(MethodArgumentNotValidException exp){
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error ->{
                    var fieldname = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldname, errorMessage);
                });;
        return ResponseEntity
            .status(BAD_REQUEST)
            .body(errors);
    }
}
