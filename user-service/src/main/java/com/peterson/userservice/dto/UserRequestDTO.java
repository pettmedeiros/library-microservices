package com.peterson.userservice.dto;

import com.peterson.userservice.model.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
    
    @NotBlank(message = "Nome obrigatório")
    String name,

    @NotBlank(message = "E-mail obrigatório")
    @Email(message = "E-mail inválido")
    String email,

    @NotBlank(message = "Senha obrigatória")
    @Size(min = 8, max = 100, message = "A senha deve possuir entre 8 e 100 caracteres")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).+$",
        message = "A senha deve conter letra maiúscula, letra minúscula, número e caractere especial"
    )
    String password,

    @NotNull(message = "Perfil obrigatório")
    Role role
) {} 
    
