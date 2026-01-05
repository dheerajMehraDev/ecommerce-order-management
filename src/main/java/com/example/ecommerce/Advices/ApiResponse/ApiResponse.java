package com.example.ecommerce.Advices.ApiResponse;

import com.example.ecommerce.Advices.ExceptionHandling.ApiError;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class ApiResponse<T> {

    private T data;

    private ApiError apiError;

}
