package com.peterson.userservice.model;

import java.util.UUID;

import com.peterson.userservice.model.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(nullable = false, length =  150)
    @NotBlank(message = "Nome obrigatório")
    String name;

    @Column(nullable = false, length =  150)
    @NotBlank(message = "E-mail obrigatório")
    @Email(message = "E-mail inválido")
    String email;

    @Column(nullable = false)
    @NotBlank(message = "Senha obrigatória")
    String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Role role;

    public User() {
    }

    public User(UUID id, @NotBlank(message = "Nome obrigatório") String name,
            @NotBlank(message = "E-mail obrigatório") @Email(message = "E-mail inválido") String email,
            @NotBlank(message = "Senha obrigatória") String password, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    
}
