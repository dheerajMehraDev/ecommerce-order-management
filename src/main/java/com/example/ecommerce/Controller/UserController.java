package com.example.ecommerce.Controller;

import com.example.ecommerce.DTO.UserDto;
import com.example.ecommerce.Entity.User;
import com.example.ecommerce.Repository.UserRepository;
import com.example.ecommerce.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


// create user

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto dto){
       return userService.createUser(dto);
    }

// get user

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id ){
        return userService.findById(id);
    }


// get all user

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll(){
        return userService.findAllUsers();
    }

// delete user

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id ){
       return userService.deleteById(id);
    }

// update the user

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable Long id , @RequestBody UserDto userDto) throws Exception {
        return userService.updateUserById(id,userDto);
    }


// partially update the user
    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> partiallyUpdateTheUserById(@PathVariable Long id , @RequestBody Map<String, Object> map) throws Exception {
        return userService.partiallyUpdateUserById(id,map);
    }


// handle exception in this controller

    public ResponseEntity<String> handlerExceptionInUserController(Exception e){
        return ResponseEntity.badRequest().build();
    }


















}
