package com.peterson.userservice.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.peterson.userservice.dto.LoginRequestDTO;
import com.peterson.userservice.dto.LoginResponseDTO;
import com.peterson.userservice.model.User;
import com.peterson.userservice.repository.UserRepository;

@Service
public class AuthService {

    UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder;
    JwtService jwtService;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponseDTO login (LoginRequestDTO dto){

         // Procura o usuário pelo e-mail
        User user = userRepository.findByEmail(dto.email()).
            orElseThrow(() -> new RuntimeException("E-mail não encontrado!"));

        // Compara a senha digitada com a senha criptogrfada do banco de dados 
        boolean senhaValida = 
            passwordEncoder.matches(dto.password(), user.getPassword());

        if(!senhaValida){
            throw new RuntimeException("Senha inválida!");
        }

           String token = jwtService.generateToken(user.getEmail());


        return new LoginResponseDTO(token);
        
    }

    
    
}
