package com.project.hiato.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<String> business(BusinessRuleException exception){
        ResponseEntity<String> response;
        response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        return response;
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> conflict(ConflictException exception){
        ResponseEntity<String> response;
        response = ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
        return response;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resource(ResourceNotFoundException exception){
        ResponseEntity<String> response;
        response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        return response;
    }

}
