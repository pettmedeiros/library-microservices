package com.peterson.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peterson.userservice.dto.LoginRequestDTO;
import com.peterson.userservice.dto.LoginResponseDTO;
import com.peterson.userservice.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity <LoginResponseDTO> login (@RequestBody LoginRequestDTO dto){

        LoginResponseDTO response = authService.login(dto);

        return ResponseEntity.ok(response);
    }

    
}
