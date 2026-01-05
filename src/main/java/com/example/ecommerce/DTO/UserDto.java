package com.example.ecommerce.DTO;

import com.example.ecommerce.Validation.passwordValidationAnnotation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id ;

    @NotBlank(message = "name can not be blank")
    private String name;

    @Email(message = "please provide valid email address")
    private String email;

    @passwordValidationAnnotation
    private String password;
}
