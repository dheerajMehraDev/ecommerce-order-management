package com.example.ecommerce.Controller;

import com.example.ecommerce.DTO.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/getSecretKey")
    public String getSecretTestMethod(){
        return "this is secret key ";
    }

    @GetMapping("/{id}")
    public UserDto getUerDto(@PathVariable Long id ){
        return new UserDto(id,"dheeraj", "dheeraj@gmail.com","secretPassword");
    }

    @GetMapping("/get")
    public String useRequestParam(@RequestParam Integer age, @RequestParam String sortBy){
        return " the age is " + age + "and sortBy " + sortBy;
    }

}
