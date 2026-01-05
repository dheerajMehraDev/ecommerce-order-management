package com.example.ecommerce.Advices.ExceptionHandling;

import com.example.ecommerce.Advices.ApiResponse.ApiResponse;
import com.example.ecommerce.Advices.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleAllExceptions(ResourceNotFoundException e){


       ApiError apiError = ApiError.builder().message(e.getMessage())
               .httpStatus(HttpStatus.BAD_REQUEST).build();
       ApiResponse apiResponse = ApiResponse.builder().apiError(apiError).build();
       return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleAllExceptions(MethodArgumentNotValidException e){

        List<String> list =
                e.getBindingResult().getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        ApiError apiError = ApiError.builder().message(list.toString())
                .httpStatus(HttpStatus.BAD_REQUEST).build();
        ApiResponse apiResponse = ApiResponse.builder().apiError(apiError).build();
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

}
