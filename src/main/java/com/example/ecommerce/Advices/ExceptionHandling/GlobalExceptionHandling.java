package com.example.ecommerce.Advices.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllExceptions(Exception e){
       ApiError apiError = ApiError.builder().message(e.getMessage())
               .httpStatusCode(HttpStatus.BAD_REQUEST).build();
       return ResponseEntity.badRequest().body(apiError);
    }

}
