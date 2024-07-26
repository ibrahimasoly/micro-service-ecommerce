package com.ibe.order.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ibe.order.exception.BusnessException;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusnessException.class)
    public ResponseEntity<String> busnessExceptionHandler(BusnessException exception){
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(exception.getMessage());
    }



     @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
    var errors = new HashMap<String, String>();
    exp.getBindingResult().getAllErrors()
            .forEach(error -> {
              var fieldName = ((FieldError) error).getField();
              var errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });

    return ResponseEntity
            .status(BAD_REQUEST)
            .body(errors);
  }


  @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handlerEntityNotFoundException(EntityNotFoundException exp){
        return ResponseEntity
            .status(BAD_REQUEST)
            .body(exp.getMessage());
    }
}
