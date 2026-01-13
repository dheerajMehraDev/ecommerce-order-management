package com.example.ecommerce.DTO;

import com.example.ecommerce.Validation.PasswordValidationAnnotation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpDto {
    @NotBlank( message = "filed can not be blank")
    private String name;

    @NotBlank( message = "filed can not be blank")
    @Email(message = "please provide valid email")
    private String email;

    @PasswordValidationAnnotation(message = "password is not secure enough")
    private String password;
}
