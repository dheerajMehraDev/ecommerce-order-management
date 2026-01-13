package com.example.ecommerce.Auth;

import com.example.ecommerce.Advices.ApiResponse.ApiResponse;
import com.example.ecommerce.DTO.LoginDto;
import com.example.ecommerce.DTO.SignUpDto;
import com.example.ecommerce.DTO.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody @Valid  SignUpDto dto){
        return authService.signUp(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginDto dto, HttpServletRequest request , HttpServletResponse response){
        String token =  authService.login(dto,response);
        return token;
    }


}
