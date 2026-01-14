package com.example.ecommerce;

import com.example.ecommerce.Auth.JwtService;
import com.example.ecommerce.Entity.User;
import com.example.ecommerce.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
//@DataJpaTest
//@ActiveProfiles("test")
@Slf4j
class EcommerceApplicationTests {

	/*@Autowired
	UserRepository userRepository;
	@Autowired
	JwtService jwtService;*/
	@Test
	void contextLoads() {
	}
	/*@Test
	void testTheDynamicQueryMethods(){
		long count = userRepository.count();
		System.out.println(count);
		Optional<User> byEmail = userRepository.findByEmail("random@gmail.com");
		Optional<User> byEmail1 = userRepository.findByEmail("admin@example.com");
		byEmail.ifPresent(System.out::println);
		byEmail1.ifPresent(System.out::println);
		for (User a : userRepository.findByNameContainingIgnoreCase("a")) {
			System.out.println(a);
		}
		List<User> byCreatedAtBefore = userRepository.findByCreatedAtBefore(LocalDateTime.now());
		byCreatedAtBefore.stream().forEach(System.out::println);
		Optional<User> userByEmail = userRepository.findUserByEmail("john@example.com");

		userByEmail.ifPresent(System.out::println);

		Optional<User> byEmailNative = userRepository.findByEmailNative("john@example.com");
		byEmailNative.ifPresent(System.out::println);
	}*/

	/*@Test
	void testIProjection(){
		List<IUserDto> allUsers = userRepository.findAllUsers();
		allUsers.forEach(user ->{
			System.out.println(user.getId());
			System.out.println(" ");
			System.out.println(user.getName());
		});
	}*/

	/*@Test
	void testConcreteProjection(){
		List<UserDtoProjection> allUsers = userRepository.findAllConcreteUsers();
		allUsers.forEach(System.out::println);
	}*/

	/*@Test
	void testJwtGenerationAndVerify(){
		User user = User.builder().id(1L).name("dheeraj").password("sdkjfs").build();
		String token = jwtService.generateAccessJwt(user);
		System.out.println(token);
		Long id = jwtService.getUserIdFromToken(token);
		System.out.println(id);
	}*/

	@Test
	void test1(){
		log.info("test1");
	}

	@Test
	void test2(){
		log.info("test2");
	}

	@BeforeEach
	void test3(){
		log.info("beore each running...");
	}
	@AfterEach
	void test4(){
		log.info("after each running");
	}

	@BeforeAll
	static void test5(){
		log.info("running before all ..");
	}
	@AfterAll
	static void test6(){
		log.info("running after all");
	}
	@Test
	@DisplayName("custom test")
	void test7(){
		log.info("test7");
	}

	@Test
	@Disabled
	void test8(){
		log.info("this will not run");
	}

	@Test
	void test9(){
		int a = 0 ;
		int b = 2;
		int result = a + b;
		int expected  = calc(a,b);
		Assertions.assertEquals(expected,result);
	}
	int calc(int a , int b){
		return a + b ;
	}

	@Test
	void test10(){
		int expected = 10;
		int result = calc(5,5);
		assertThat(result).isEqualTo(expected);
	}
	double devide(int a , int b){
		return a / b;
	}
	@Test
	void test11(){
		int a = 10;
		int b = 0;
		assertThatThrownBy(() -> devide(a, b))
				.isInstanceOf(ArithmeticException.class)
				.hasMessage("/ by zero");

	}

}
