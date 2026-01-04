package com.example.ecommerce.Controller;

import com.example.ecommerce.Entity.User;
import com.example.ecommerce.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;


// create user

    @PostMapping
    public User createUser(@RequestBody User user){
       return userRepository.save(user);
    }

// get user

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id ){
        return userRepository.findById(id).orElseThrow();
    }


// get all user

    @GetMapping
    public List<User> getAll(){
        return userRepository.findAll();
    }

// delete user

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id ){
        userRepository.deleteById(id);
        return true;
    }

// update the user


// partially update the user






















}
