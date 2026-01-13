package com.example.ecommerce.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {
    @Test
    public void test1(){
        System.out.println("test1");
    }
}
