package com.example.ecommerce.Controller;

import com.example.ecommerce.DTO.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public String getSecretTestMethod(){
        return "this is secret key ";
    }

    @GetMapping("/{id}")
    public UserDto getUerDto(@PathVariable Long id ){
        return new UserDto(id,"dheeraj", "dheeraj@gmail.com","secretPassword");
    }

}
