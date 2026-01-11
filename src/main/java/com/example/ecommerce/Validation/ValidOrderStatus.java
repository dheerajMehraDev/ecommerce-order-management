package com.example.ecommerce.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = OrderStatusValidatorClass.class)
public @interface ValidOrderStatus {
    String message() default "password should contain lowercase , uppercase , number and special character";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
