package com.example.ecommerce.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class OrderStatusValidatorClass implements ConstraintValidator<ValidOrderStatus,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        List<String> validation = List.of("PAID", "CREATED", "SHIPPED");
        return validation.contains(s);
    }
}
