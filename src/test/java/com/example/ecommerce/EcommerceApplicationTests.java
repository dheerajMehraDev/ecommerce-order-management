package com.example.ecommerce;

import com.example.ecommerce.DTO.Projection.IUserDto;
import com.example.ecommerce.DTO.Projection.UserDtoProjection;
import com.example.ecommerce.Entity.User;
import com.example.ecommerce.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class EcommerceApplicationTests {

	@Autowired
	UserRepository userRepository;
	@Test
	void contextLoads() {
	}
	@Test
	void testTheDynamicQueryMethods(){
		/*long count = userRepository.count();
		System.out.println(count);
		Optional<User> byEmail = userRepository.findByEmail("random@gmail.com");
		Optional<User> byEmail1 = userRepository.findByEmail("admin@example.com");
		byEmail.ifPresent(System.out::println);
		byEmail1.ifPresent(System.out::println);*/
	/*	for (User a : userRepository.findByNameContainingIgnoreCase("a")) {
			System.out.println(a);
		}*/
		/*List<User> byCreatedAtBefore = userRepository.findByCreatedAtBefore(LocalDateTime.now());
		byCreatedAtBefore.stream().forEach(System.out::println);*/
//		Optional<User> userByEmail = userRepository.findUserByEmail("john@example.com");
//
//		userByEmail.ifPresent(System.out::println);

		Optional<User> byEmailNative = userRepository.findByEmailNative("john@example.com");
		byEmailNative.ifPresent(System.out::println);
	}

	@Test
	void testIProjection(){
		List<IUserDto> allUsers = userRepository.findAllUsers();
		allUsers.forEach(user ->{
			System.out.println(user.getId());
			System.out.println(" ");
			System.out.println(user.getName());
		});
	}

	@Test
	void testConcreteProjection(){
		List<UserDtoProjection> allUsers = userRepository.findAllConcreteUsers();
		allUsers.forEach(System.out::println);
	}


}
