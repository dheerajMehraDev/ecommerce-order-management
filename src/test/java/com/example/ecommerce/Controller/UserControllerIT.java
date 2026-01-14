package com.example.ecommerce.Controller;

import com.example.ecommerce.Advices.ApiResponse.ApiResponse;
import com.example.ecommerce.DTO.UserDto;
import com.example.ecommerce.Entity.User;
import com.example.ecommerce.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class UserControllerIT {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    private User user;
    private UserDto dto;
    @Autowired
    EntityManager entityManager;


    @BeforeEach
    void setup(){
         user = User.builder().name("dheeraj").email("dm@gmail.com")
                .password("dheeraj123").build();
        entityManager.clear();
    }


    @Test
    void test1(){
        User savedUser = userRepository.save(user);
         dto  = modelMapper.map(savedUser, UserDto.class);
        webTestClient.get().uri("/user/{id}" , savedUser.getId())
                .exchange()
                .expectStatus().isOk();


    }
}
