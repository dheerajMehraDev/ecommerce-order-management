package com.example.ecommerce.Controller;

import com.example.ecommerce.DTO.UserDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
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

    @PostMapping
    public Boolean createUser(@RequestBody UserDto userDto){
        System.out.println("created the user with user id "+ userDto.getId());
        return true;
    }

    @PutMapping
    public Boolean updateUser(){
        System.out.println("updated ");
        return true;
    }

}
