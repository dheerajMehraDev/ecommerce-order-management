package com.example.ecommerce.DTO;

import com.example.ecommerce.DTO.OrderItemDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    @NotNull(message = "user id must be there for placing an order")
    private Long userId;
    @NotBlank(message = "orderItems should not be blank")
    private List<OrderItemDto> items;
}
