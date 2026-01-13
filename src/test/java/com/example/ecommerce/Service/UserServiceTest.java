package com.example.ecommerce.Service;

import com.example.ecommerce.DTO.UserDto;
import com.example.ecommerce.Entity.User;
import com.example.ecommerce.Repository.UserRepository;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;


    @Test
    public void test_findById_whenIdIsValid_returnValidUser(){
        User user = User.builder().id(1L).name("dheeraj").email("dm@gmail.com")
                .build();
        UserDto userDto = modelMapper.map(user,UserDto.class);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

        Assertions.assertThat(userService.findById(1L).getBody().getId())
                .isEqualTo(userDto.getId());
        verify(userRepository,atMostOnce()).findById(1L);

    }
}
