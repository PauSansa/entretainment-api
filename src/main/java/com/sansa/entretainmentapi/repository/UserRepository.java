package com.sansa.entretainmentapi.repository;

import com.sansa.entretainmentapi.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, UUID> {
    Mono<User> findByUsername(String username);
}
