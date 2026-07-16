package com.peterson.userservice.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.peterson.userservice.repository.UserRepository;
import com.peterson.userservice.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtService jwtService;
    private final UserRepository userRepository;
    
    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       // Obtém o cabeçalho Authorization
        String authorizationHeader = request.getHeader("Authorization");

        // Verifica se existe o cabeçalho e se começa com "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            // Remove "Bearer " do início do token
            String token = authorizationHeader.substring(7);

            // Valida o token
            if (jwtService.validateToken(token)) {

                // Extrai o e-mail do token
                String email = jwtService.extractEmail(token);

                // Procura o usuário no banco
                var user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

                // Cria um objeto de autenticação para o Spring Security
                 UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            null);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Continua a requisição
        filterChain.doFilter(request, response);
    }

    
    
}
