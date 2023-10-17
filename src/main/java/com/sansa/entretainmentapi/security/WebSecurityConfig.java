package com.sansa.entretainmentapi.security;


import com.sansa.entretainmentapi.security.jwt.JwtWebFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
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

