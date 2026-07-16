package com.peterson.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Como a API utiliza JWT (stateless), o CSRF pode ser desabilitado.
            .csrf(csrf -> csrf.disable())

            // Não cria sessão HTTP.
            // Cada requisição deve enviar um JWT válido.
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // Configuração das permissões
            .authorizeHttpRequests(auth -> auth

                // Login é público
                .requestMatchers("/auth/login").permitAll()

                // Cadastro de usuário é público
                .requestMatchers(HttpMethod.POST, "/users").permitAll()

                // Qualquer outra rota exige autenticação
                .anyRequest().authenticated()
            )

            // Executa o filtro JWT antes do filtro padrão do Spring Security
            .addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

    // Bean responsável por criptografar e verificar senhas
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}