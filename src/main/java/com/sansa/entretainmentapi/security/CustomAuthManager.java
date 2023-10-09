package com.sansa.entretainmentapi.security;

import com.sansa.entretainmentapi.security.jwt.JwtService;
import com.sansa.entretainmentapi.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class CustomAuthManager implements ReactiveAuthenticationManager {
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getCredentials().toString();
        Claims claims = jwtService.getAllClaimsFromToken(token);

        String username = claims.getSubject();
        String authorities = claims.get("authorities").toString();
        Mono<UserDetails> userDetails = userDetailsService.findByUsername(username);

        if(jwtService.isTokenValid(token)){
            return Mono.just(new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(authorities)));
        } else {
            return Mono.empty();
        }
    }
}
