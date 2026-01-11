package com.example.ecommerce.Repository;

import com.example.ecommerce.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    // 🔹 AUTH / LOGIN
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);

    // 🔹 SEARCH / FILTER
    List<User> findByNameContainingIgnoreCase(String name);
    List<User> findByCreatedAtAfter(LocalDateTime date);
    List<User> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    // 🔹 SORTING
    List<User> findAllByOrderByCreatedAtDesc();

    // 🔹 DELETE
    void deleteByEmail(String email);

    // 🔹 COUNT
    long countByCreatedAtAfter(LocalDateTime date);
}
