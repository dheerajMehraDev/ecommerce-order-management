package com.example.ecommerce.Advices.ExceptionHandling;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
@Builder
public class ApiError {
    private HttpStatusCode httpStatus;

    private String message;
}
