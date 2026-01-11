package com.example.ecommerce.Repository;

import com.example.ecommerce.DTO.IUserDto;
import com.example.ecommerce.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);

    // 🔹 SEARCH / FILTER
    List<User> findByNameContainingIgnoreCase(String name);
    List<User> findByCreatedAtAfter(LocalDateTime date);
    List<User> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    
    // 🔹 DELETE
    void deleteByEmail(String email);

    // 🔹 COUNT
    long countByCreatedAtAfter(LocalDateTime date);

    List<User>  findByCreatedAtBefore(LocalDateTime now);

    @Query("SELECT u FROM User u WHERE u.email = :email1")
    Optional<User> findUserByEmail(@Param("email1") String email);

    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    Optional<User> findByEmailNative(String email);

    List<User> findByNameContainingIgnoreCaseOrderByEmail(String a);

    @Query("""
       SELECT u.id AS id,
              u.name AS name
       FROM User u
       """)
    List<IUserDto> findAllUsers();
}
