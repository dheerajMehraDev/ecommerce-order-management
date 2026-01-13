package com.example.ecommerce.Auth;

import com.example.ecommerce.Entity.Session;
import com.example.ecommerce.Entity.User;
import com.example.ecommerce.Repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {
    private static final Integer MAX_SESSION = 2;
    private final SessionRepository sessionRepository;

    public void setSession(User user, String refreshToken) {
       List<Session> sessionList =  sessionRepository.findByUser(user);
       if(sessionList.size() == MAX_SESSION){
           invalidateLRUSession(sessionList);
       }
       Session session = Session.builder().refreshToken(refreshToken)
               .user(user).build();
       sessionRepository.save(session);
    }

    private void invalidateLRUSession(List<Session> sessionList) {
        Session sessionToBeRemoved = sessionList.stream()
                .sorted(Comparator.comparing(session -> session.getLastUsedAt()))
                .findFirst().orElseThrow();
        sessionRepository.delete(sessionToBeRemoved);
    }

    public void validateSession(String refreshToken) {
        boolean exist = sessionRepository.existsByRefreshToken(refreshToken);
        if(!exist) throw new BadCredentialsException("session does not exist");
    }
}
