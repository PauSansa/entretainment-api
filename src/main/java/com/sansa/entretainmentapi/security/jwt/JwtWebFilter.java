package com.sansa.entretainmentapi.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtWebFilter implements WebFilter {
    private final JwtService jwtService;
    private final ReactiveUserDetailsService userDetailsService;
    private final ReactiveAuthenticationManager authenticationManager;
    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            WebFilterChain chain) {
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return chain.filter(exchange);
        }

        String token = authorizationHeader.substring(7);
        String userEmail = jwtService.getUsernameFromToken(token);

        if (userEmail == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            return chain.filter(exchange);
        }

        return userDetailsService.findByUsername(userEmail)
                .flatMap(userDetails -> {
                    if (jwtService.isTokenValid(token, userDetails)) {
                        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    } else {
                        return Mono.error(new AuthenticationCredentialsNotFoundException("Invalid Token"));
                    }
                    System.out.println(SecurityContextHolder.getContext().getAuthentication());
                    return chain.filter(exchange);
                })
                .onErrorResume(AuthenticationException.class, e -> {
                    // Manejo de excepción
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    System.out.println(e.getMessage());
                    return exchange.getResponse().setComplete();
                });
    }
}
