package com.example.ecommerce.Service;

import com.example.ecommerce.DTO.UserDto;
import com.example.ecommerce.Entity.User;
import com.example.ecommerce.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private  final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public Boolean deleteById(Long id) {
        boolean isExist = userRepository.existsById(id);
        if(!isExist) return false;
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

    public UserDto updateUserById(Long id, UserDto userDto) throws Exception {
        boolean isExist = userRepository.existsById(id);
        if(!isExist) throw new Exception("User does not exist");

        User user = modelMapper.map(userDto , User.class);
        return modelMapper.map(userRepository.save(user) , UserDto.class);
    }


    public UserDto partiallyUpdateUserById(Long id, Map<String, Object> map) throws Exception {

        boolean isExist = userRepository.existsById(id);
        if(!isExist) throw new Exception("employee does not exist");

        User user = userRepository.findById(id).orElseThrow();

        map.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(User.class , key);
            field.setAccessible(true);
            ReflectionUtils.setField(field,user,value);
        });
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser,UserDto.class);

    }
}
