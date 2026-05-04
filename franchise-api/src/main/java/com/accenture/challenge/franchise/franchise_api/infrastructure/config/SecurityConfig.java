package com.accenture.challenge.franchise.franchise_api.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;



@Configuration
@EnableWebFluxSecurity // <--- Única anotación necesaria para WebFlux ✅
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                // 1. Desactivamos CSRF para permitir POST/PATCH sin tokens en el reto
                .csrf(ServerHttpSecurity.CsrfSpec::disable)

                // 2. Configuramos los permisos
                .authorizeExchange(exchanges -> exchanges
                        // Permitimos acceso total a Swagger y documentación
                        .pathMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/webjars/**")
                        .permitAll()
                        // El resto de la API requiere autenticación
                        .anyExchange().authenticated())

                // 3. Habilitamos autenticación básica (Basic Auth)
                .httpBasic(Customizer.withDefaults())

                // 4. Desactivamos formularios de login (opcional para APIs)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails user = User.builder()
                .username("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        return new MapReactiveUserDetailsService(user);
    }
}