package com.example.ecommerce.Auth;

import com.example.ecommerce.Advices.ApiResponse.ApiResponse;
import com.example.ecommerce.DTO.LoginDto;
import com.example.ecommerce.DTO.SignUpDto;
import com.example.ecommerce.DTO.UserDto;
import com.example.ecommerce.Entity.User;
import com.example.ecommerce.Repository.UserRepository;
import io.jsonwebtoken.Jwt;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;



    public ResponseEntity<UserDto> signUp(SignUpDto dto) {
        if(userRepository.existsByEmail(dto.getEmail()))
            throw new BadCredentialsException("user already exists with this email");
        User user = modelMapper.map(dto , User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        UserDto userDto = modelMapper.map(savedUser , UserDto.class);
        return ResponseEntity.ok(userDto);
    }

    public String login(@Valid LoginDto dto) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);

        User user = (User) authenticate.getPrincipal();
        String token = jwtService.generateJwt(user);
        return token;
    }
}
