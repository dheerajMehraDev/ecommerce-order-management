package com.example.ecommerce.Advices.ExceptionHandling;

import com.example.ecommerce.Advices.ApiResponse.ApiResponse;
import com.example.ecommerce.Advices.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandling {

    // 404 - resource not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException e) {

        ApiError apiError = ApiError.builder()
                .message(e.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.builder().apiError(apiError).build());
    }

    // 400 - validation error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException e) {

        List<String> errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        ApiError apiError = ApiError.builder()
                .message(errors.toString())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();

        return ResponseEntity
                .badRequest()
                .body(ApiResponse.builder().apiError(apiError).build());
    }

    // 400 - bad JSON / wrong input
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalArgument(IllegalArgumentException e) {
        return buildError(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // 500 - fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneric(Exception e) {
        return buildError("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ApiResponse<?>> buildError(String msg, HttpStatus status) {
        ApiError apiError = ApiError.builder()
                .message(msg)
                .httpStatus(status)
                .build();

        return ResponseEntity
                .status(status)
                .body(ApiResponse.builder().apiError(apiError).build());
    }
}
