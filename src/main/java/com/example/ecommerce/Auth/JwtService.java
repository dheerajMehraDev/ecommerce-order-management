package com.example.ecommerce.Auth;

import com.example.ecommerce.Entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {


    @Value(("${secret.key}"))
    private String secretKey;


    public Key getSecretKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
    public String generateJwt(User user){
        return
                Jwts.builder()
                        .setSubject(user.getId().toString())
                        .claim("email" , user.getEmail())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 120))
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

}
