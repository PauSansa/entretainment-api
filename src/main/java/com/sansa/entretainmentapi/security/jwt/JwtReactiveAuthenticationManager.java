package com.sansa.entretainmentapi.security.jwt;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@Qualifier("jwt")
public class JwtReactiveAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtUtil jwtUtil;
    private final ReactiveUserDetailsService userDetailsService;

    @Autowired
    public JwtReactiveAuthenticationManager(JwtUtil jwtUtil, ReactiveUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = (String) authentication.getCredentials();

        String username;
        try {
            if(!this.jwtUtil.verify(token)) throw new JwtException("Invalid JWT");
            username = this.jwtUtil.getSubject(token);
        } catch (Exception e) {
            return Mono.error(new BadCredentialsException("invalid credentials"));
        }

        return this.userDetailsService.findByUsername(username)
                .switchIfEmpty(Mono.error(new BadCredentialsException("invalid credentials")))
                .map(userDetails -> new JwtAuthenticationToken(userDetails, token, userDetails.getAuthorities()));
    }
}
