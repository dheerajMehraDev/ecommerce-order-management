package com.example.ecommerce.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Product name must not be empty")
    @Size(
            min = 2,
            max = 100,
            message = "Product name must be between 2 and 100 characters"
    )
    private String name;

    @Size(
            max = 500,
            message = "Description must not exceed 500 characters"
    )
    private String description;

    @NotNull(message = "Product price is required")
    @DecimalMin(
            value = "0.0",
            inclusive = false,
            message = "Price must be greater than 0"
    )
    @Digits(
            integer = 10,
            fraction = 2,
            message = "Price must have up to 10 digits and 2 decimal places"
    )
    private BigDecimal price;

    @NotNull(message = "Product quantity is required")
    @Min(
            value = 0,
            message = "Quantity cannot be negative"
    )
    private Integer quantity;

    @NotBlank(message = "Product category must not be empty")
    @Size(
            max = 50,
            message = "Category must not exceed 50 characters"
    )
    private String category;

    @NotNull(message = "Product active status must be specified")
    private Boolean active;
}
