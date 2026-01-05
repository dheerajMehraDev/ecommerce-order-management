package com.example.ecommerce.Service;

import com.example.ecommerce.DTO.UserDto;
import com.example.ecommerce.Entity.User;
import com.example.ecommerce.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserService {

    private  final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public ResponseEntity<Boolean> deleteById(Long id) {
        boolean isExist = userRepository.existsById(id);
        if(!isExist) return ResponseEntity.notFound().build();
        userRepository.deleteById(id);
         return ResponseEntity.ok(Boolean.TRUE);
    }

    public ResponseEntity<List<UserDto>> findAllUsers() {
         List<UserDto> userDtoList =   userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
         return ResponseEntity.ok(userDtoList);
    }

    public ResponseEntity<UserDto> findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
    }

    public ResponseEntity<UserDto> createUser(UserDto dto) {
        User user = modelMapper.map(dto , User.class);
        User saved = userRepository.save(user);
        return ResponseEntity.ok(modelMapper.map(saved, UserDto.class));
    }

    public ResponseEntity<UserDto> updateUserById(Long id, UserDto userDto) throws Exception {
        boolean isExist = userRepository.existsById(id);
        if(!isExist) return ResponseEntity.notFound().build();


        User user = modelMapper.map(userDto , User.class);
        UserDto dto = modelMapper.map( userRepository.save(user) , UserDto.class);
        return ResponseEntity.ok(dto);
    }


    public ResponseEntity<UserDto> partiallyUpdateUserById(Long id, Map<String, Object> map) throws Exception {

        boolean isExist = userRepository.existsById(id);
        if(!isExist) ResponseEntity.notFound().build();

        User user = userRepository.findById(id).orElseThrow();

        map.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(User.class , key);
            field.setAccessible(true);
            ReflectionUtils.setField(field,user,value);
        });
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(modelMapper.map(savedUser,UserDto.class));

    }
}
