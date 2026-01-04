package com.example.ecommerce.Controller;

import com.example.ecommerce.Entity.User;
import com.example.ecommerce.Repository.UserRepository;
import com.example.ecommerce.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


// create user

    @PostMapping
    public User createUser(@RequestBody User user){
       return userService.createUser(user);
    }

// get user

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id ){
        return userService.findById(id);
    }


// get all user

    @GetMapping
    public List<User> getAll(){
        return userService.findAllUsers();
    }

// delete user

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id ){
       return userService.deleteById(id);
    }

// update the user


// partially update the user






















}
