package com.example.ecommerce.Auth;

import com.example.ecommerce.Advices.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.example.ecommerce.Entity.User;
import com.example.ecommerce.Repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final UserRepository userRepository;
    @Value(("${secret.key}"))
    private String secretKey;


    public Key getSecretKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
    public String generateAccessJwt(User user){
        return
                Jwts.builder()
                        .setSubject(user.getId().toString())
                        .claim("email" , user.getEmail())
                        .claim("role" , user.getRole().toString())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 120))
                        .signWith(getSecretKey())
                        .compact();
    }

    public String generateRefreshJwt(User user){
        return
                Jwts.builder()
                        .setSubject(user.getId().toString())
                        .claim("email" , user.getEmail())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365))
                        .signWith(getSecretKey())
                        .compact();
    }

    public Long getUserIdFromToken(String token){

        String subject = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return Long.valueOf(subject);
    }

    public User getUserFromUserId(Long userIdFromToken) {
        return userRepository.findById(userIdFromToken).orElseThrow(
                () -> new ResourceNotFoundException("user not found")
        );
    }
}
