package com.example.ecommerce.DTO;

import com.example.ecommerce.Validation.PasswordValidationAnnotation;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class LoginDto {
    @Email
    private String email;
    @PasswordValidationAnnotation
    private String password;

}
