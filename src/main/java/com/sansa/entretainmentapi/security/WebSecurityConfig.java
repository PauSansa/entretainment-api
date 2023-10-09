package com.sansa.entretainmentapi.security;


import com.sansa.entretainmentapi.repository.UserRepository;
import com.sansa.entretainmentapi.security.jwt.JwtWebFilter;
import com.sansa.entretainmentapi.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
public class WebSecurityConfig {
    private final JwtWebFilter jwtWebFilter;
    private final CustomAuthManager authenticationManager;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .authorizeExchange(authorize -> {
                    authorize.pathMatchers(HttpMethod.OPTIONS).permitAll()
                            .pathMatchers("/auth/*").permitAll()
                            .anyExchange().authenticated();
                });
        http.authenticationManager(authenticationManager);
        http.addFilterBefore(jwtWebFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        http.securityContextRepository(NoOpServerSecurityContextRepository.getInstance());
        http.exceptionHandling(eh -> {
            eh.authenticationEntryPoint((swe, e) -> Mono.error(e));
            eh.accessDeniedHandler((swe,e ) -> Mono.error(e));
                }
        );
        return http.build();
    }


}

