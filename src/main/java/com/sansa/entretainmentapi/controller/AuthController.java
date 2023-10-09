package com.sansa.entretainmentapi.controller;

import com.sansa.entretainmentapi.model.AuthResponse;
import com.sansa.entretainmentapi.model.AuthRequest;
import com.sansa.entretainmentapi.security.jwt.JwtService;
import com.sansa.entretainmentapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private JwtService jwtUtil;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
        return userService.loadUserByUsername(ar.getUsername())
                .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<AuthResponse>> register(@RequestBody AuthRequest ar){
        return userService.createAndSaveUser(ar)
                .map(user -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(user))));
    }

    @GetMapping("/test")
    public Mono<String> test(){
        return Mono.just(SecurityContextHolder.getContext().getAuthentication().toString());
    }

}
