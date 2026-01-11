package com.example.ecommerce.DTO;

import com.example.ecommerce.Validation.ValidOrderStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {


    private Long id;

    @NotBlank
    @ValidOrderStatus(message = "status can be either of them CREATED, PAID, SHIPPED")
    private String status; // CREATED, PAID, SHIPPED
}
