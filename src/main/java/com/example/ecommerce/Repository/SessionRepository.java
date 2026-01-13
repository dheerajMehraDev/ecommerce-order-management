package com.example.ecommerce.Repository;

import com.example.ecommerce.Entity.Session;
import com.example.ecommerce.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session,Long> {

    List<Session> findByUser(User user);

    boolean existsByRefreshToken(String refreshToken);
}
