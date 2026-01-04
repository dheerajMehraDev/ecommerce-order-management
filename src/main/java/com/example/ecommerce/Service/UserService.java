package com.example.ecommerce.Service;

import com.example.ecommerce.DTO.UserDto;
import com.example.ecommerce.Entity.User;
import com.example.ecommerce.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private  final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public Boolean deleteById(Long id) {
         userRepository.deleteById(id);
         return true;
    }

    public List<UserDto> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto findById(Long id) {
        return userRepository.findById(id).
                map(user -> modelMapper.map(user , UserDto.class))
                .orElseThrow();
    }

    public UserDto createUser(UserDto dto) {
        User user = modelMapper.map(dto , User.class);
        User saved = userRepository.save(user);
        return modelMapper.map(saved , UserDto.class);
    }
}
