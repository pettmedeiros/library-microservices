package com.peterson.userservice.dto;

import com.peterson.userservice.model.enums.Role;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
    
    @NotBlank(message = "Nome obrigatório")
    String name,

    @NotBlank(message = "E-mail obrigatório")
    @Email(message = "E-mail inválido")
    String email,

    @NotBlank(message = "Senha obrigatória")
    String password,

    @Column(nullable = false)
    Role role
) {} 
    
