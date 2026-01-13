package com.example.ecommerce.Auth;

import com.example.ecommerce.Entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if(authorization == null || authorization.isBlank() || !authorization.startsWith("Bearer"))
        {
            filterChain.doFilter(request,response);
            return;
        }
            String token = authorization.split("Bearer ")[1];
        if(token.isBlank()) {
            filterChain.doFilter(request,response);
            return;
        }
        Long userIdFromToken = jwtService.getUserIdFromToken(token);
        User user = jwtService.getUserFromUserId(userIdFromToken);
        if(user != null || SecurityContextHolder.getContext() == null){
            Authentication authentication = new UsernamePasswordAuthenticationToken(user,null,null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);
    }
}
