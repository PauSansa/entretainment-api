package com.sansa.entretainmentapi.service;

import com.sansa.entretainmentapi.entity.Role;
import com.sansa.entretainmentapi.entity.User;
import com.sansa.entretainmentapi.model.AuthRequest;
import com.sansa.entretainmentapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository data;
    private final PasswordEncoder passwordEncoder;

    public Mono<User> loadUserByUsername(String username) {
        return data.findByUsername(username);
    }

    public Mono<User> createAndSaveUser(AuthRequest ar){
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setCreatedDate(LocalDate.now());
        user.setUsername(ar.getUsername());
        user.setPassword(passwordEncoder.encode(ar.getPassword()));
        user.setRole(Role.USER);
        return data.save(user);
    }
}
