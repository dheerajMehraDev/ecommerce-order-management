package com.example.ecommerce.Service;

import com.example.ecommerce.Advices.ExceptionHandling.CustomExceptions.ResourceNotFoundException;
import com.example.ecommerce.DTO.UserDto;
import com.example.ecommerce.Entity.User;
import com.example.ecommerce.Repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    /* ---------------- DELETE ---------------- */

    @Test
    void deleteById_whenUserExists_returnTrue() {

        when(userRepository.existsById(1L)).thenReturn(true);

        ResponseEntity<Boolean> response = userService.deleteById(1L);

        Assertions.assertThat(response.getBody()).isTrue();
        verify(userRepository).deleteById(1L);
    }

    @Test
    void deleteById_whenUserNotExists_throwException() {

        when(userRepository.existsById(1L)).thenReturn(false);

        Assertions.assertThatThrownBy(() -> userService.deleteById(1L))
                .isInstanceOf(ResourceNotFoundException.class);

        verify(userRepository, never()).deleteById(anyLong());
    }

    /* ---------------- FIND ALL ---------------- */

    @Test
    void findAllUsers_returnUserDtoList() {

        User user = new User();
        user.setId(1L);

        UserDto dto = new UserDto();
        dto.setId(1L);

        when(userRepository.findAll()).thenReturn(List.of(user));
        when(modelMapper.map(user, UserDto.class)).thenReturn(dto);

        ResponseEntity<List<UserDto>> response = userService.findAllUsers();

        Assertions.assertThat(response.getBody())
                .hasSize(1)
                .first()
                .extracting(UserDto::getId)
                .isEqualTo(1L);
    }

    /* ---------------- FIND BY ID ---------------- */

    @Test
    void findById_whenIdValid_returnUserDto() {

        User user = new User();
        user.setId(1L);

        UserDto dto = new UserDto();
        dto.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserDto.class)).thenReturn(dto);

        ResponseEntity<UserDto> response = userService.findById(1L);

        Assertions.assertThat(response.getBody().getId()).isEqualTo(1L);
        verify(userRepository).findById(1L);
    }

    @Test
    void findById_whenIdInvalid_throwException() {

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> userService.findById(1L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    /* ---------------- CREATE ---------------- */

    @Test
    void createUser_returnSavedUserDto() {

        UserDto inputDto = new UserDto();
        inputDto.setName("Dheeraj");

        User user = new User();
        User savedUser = new User();
        savedUser.setId(1L);

        UserDto responseDto = new UserDto();
        responseDto.setId(1L);

        when(modelMapper.map(inputDto, User.class)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(savedUser);
        when(modelMapper.map(savedUser, UserDto.class)).thenReturn(responseDto);

        ResponseEntity<UserDto> response = userService.createUser(inputDto);

        Assertions.assertThat(response.getBody().getId()).isEqualTo(1L);
    }

    /* ---------------- UPDATE ---------------- */

    @Test
    void updateUserById_whenUserExists_returnUpdatedUser() throws Exception {

        UserDto dto = new UserDto();
        User user = new User();
        User saved = new User();

        when(userRepository.existsById(1L)).thenReturn(true);
        when(modelMapper.map(dto, User.class)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(saved);
        when(modelMapper.map(saved, UserDto.class)).thenReturn(dto);

        ResponseEntity<UserDto> response =
                userService.updateUserById(1L, dto);

        Assertions.assertThat(response.getBody()).isNotNull();
    }

    /* ---------------- PARTIAL UPDATE ---------------- */

    @Test
    void partiallyUpdateUserById_whenValid_returnUpdatedUser() throws Exception {

        User user = new User();
        user.setName("Old");

        User saved = new User();
        saved.setName("New");

        UserDto dto = new UserDto();
        dto.setName("New");

        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(saved);
        when(modelMapper.map(saved, UserDto.class)).thenReturn(dto);

        ResponseEntity<UserDto> response =
                userService.partiallyUpdateUserById(
                        1L,
                        Map.of("name", "New")
                );

        Assertions.assertThat(response.getBody().getName()).isEqualTo("New");
    }
}
