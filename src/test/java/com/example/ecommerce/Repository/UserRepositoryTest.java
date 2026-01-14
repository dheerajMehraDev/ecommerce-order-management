package com.example.ecommerce.Repository;

import com.example.ecommerce.DTO.Projection.IUserDto;
import com.example.ecommerce.DTO.Projection.UserDtoProjection;
import com.example.ecommerce.Entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();

        user1 = new User();
        user1.setName("Admin");
        user1.setEmail("admin@shop.com");
        user1.setPassword("admin123");
        user1.setCreatedAt(LocalDateTime.now().minusDays(2));

        user2 = new User();
        user2.setName("John Doe");
        user2.setEmail("john@shop.com");
        user2.setPassword("john123");
        user2.setCreatedAt(LocalDateTime.now());

        userRepository.saveAll(List.of(user1, user2));
    }

    @Test
    void findByEmail() {
        Optional<User> user = userRepository.findByEmail("admin@shop.com");
        assertThat(user).isPresent();
    }

    @Test
    void findByEmailAndPassword() {
        Optional<User> user = userRepository
                .findByEmailAndPassword("admin@shop.com", "admin123");
        assertThat(user).isPresent();
    }

    @Test
    void existsByEmail() {
        boolean exists = userRepository.existsByEmail("john@shop.com");
        assertThat(exists).isTrue();
    }

    @Test
    void findByNameContainingIgnoreCase() {
        List<User> users = userRepository.findByNameContainingIgnoreCase("john");
        assertThat(users).hasSize(1);
    }

    @Test
    void findByCreatedAtAfter() {
        List<User> users =
                userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1));
        assertThat(users).hasSize(1);
    }

    @Test
    void findByCreatedAtBetween() {
        List<User> users =
                userRepository.findByCreatedAtBetween(
                        LocalDateTime.now().minusDays(3),
                        LocalDateTime.now()
                );
        assertThat(users).hasSize(2);
    }

    @Test
    void deleteByEmail() {
        userRepository.deleteByEmail("admin@shop.com");
        assertThat(userRepository.existsByEmail("admin@shop.com")).isFalse();
    }

    @Test
    void countByCreatedAtAfter() {
        long count =
                userRepository.countByCreatedAtAfter(LocalDateTime.now().minusDays(1));
        assertThat(count).isEqualTo(1);
    }

    @Test
    void findByCreatedAtBefore() {
        List<User> users =
                userRepository.findByCreatedAtBefore(LocalDateTime.now().minusDays(1));
        assertThat(users).hasSize(1);
    }

    @Test
    void findUserByEmail_JPQL() {
        Optional<User> user = userRepository.findUserByEmail("john@shop.com");
        assertThat(user).isPresent();
    }

    @Test
    void findByEmailNative() {
        Optional<User> user = userRepository.findByEmailNative("admin@shop.com");
        assertThat(user).isPresent();
    }

    @Test
    void findByNameContainingIgnoreCaseOrderByEmail() {
        List<User> users =
                userRepository.findByNameContainingIgnoreCaseOrderByEmail("o");
        assertThat(users).hasSize(1);
    }

    @Test
    void findAllUsers_InterfaceProjection() {
        List<IUserDto> users = userRepository.findAllUsers();
        assertThat(users).hasSize(2);
        assertThat(users.get(0).getId()).isNotNull();
    }

    @Test
    void findAllConcreteUsers_ClassProjection() {
        List<UserDtoProjection> users =
                userRepository.findAllConcreteUsers();
        assertThat(users).hasSize(2);
        assertThat(users.get(0).getName()).isNotNull();
    }
}
