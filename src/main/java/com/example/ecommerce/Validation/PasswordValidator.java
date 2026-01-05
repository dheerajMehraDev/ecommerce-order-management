package com.example.ecommerce.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<passwordValidationAnnotation,String> {


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if( s == null || s.isBlank() ) return false;
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecialchar = false;

        for(Character c : s.toCharArray()){
            if(Character.isLowerCase(c)) hasLower = true;
            else if(Character.isUpperCase(c)) hasUpper = true;
            else if(Character.isDigit(c)) hasNumber = true;
            else {
                hasSpecialchar = true;
            }
        }
        return (hasNumber && hasLower && hasUpper && hasSpecialchar);
    }
}
