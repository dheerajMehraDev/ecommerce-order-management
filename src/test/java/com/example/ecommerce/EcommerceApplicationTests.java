package com.example.ecommerce;

import com.example.ecommerce.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EcommerceApplicationTests {

	@Autowired
	UserRepository userRepository;
	@Test
	void contextLoads() {
	}
	@Test
	void testTheDynamicQueryMethods(){

	}


}
