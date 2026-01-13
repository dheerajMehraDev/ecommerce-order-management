package com.example.ecommerce.Controller;

import com.example.ecommerce.DTO.UserDto;
import com.example.ecommerce.Entity.User;
import com.example.ecommerce.Repository.UserRepository;
import com.example.ecommerce.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;


// create user

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto dto){
       return userService.createUser(dto);
    }

// get user
    @Secured("ROLE_USER")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id ){
        return userService.findById(id);
    }


// get all user

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
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



    @GetMapping("/testSort")
    public Page<User> getListOfUser(@RequestParam(defaultValue = "id") String sortBy,
                                    @RequestParam(defaultValue = "1") Integer number ,
                                    @RequestParam(defaultValue = "5" ) Integer size
                                    )
    {
//        return userRepository.findByNameContainingIgnoreCaseOrderByEmail("a");
//            return userRepository.findAll(Sort.by(Sort.Direction.DESC , sortBy));
        Sort sort = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(number,size,sort);
        return   userRepository.findAll(pageable);
    }

/*// handle exception in this controller

    public ResponseEntity<String> handlerExceptionInUserController(Exception e){
        return ResponseEntity.badRequest().build();
    }*/


















}
