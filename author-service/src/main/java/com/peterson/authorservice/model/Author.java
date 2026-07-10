package com.peterson.authorservice.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Author {
   
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    
    @Column(nullable = false, length =  100)
    @NotBlank(message = "Nome obrigatório")
    String name;

    @Column(nullable = false, length = 50, unique = true )
    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail obrigatório")
    String email;
    
    String biography;

    public Author() {
    }

    public Author(UUID id, String name, String email, String biography) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.biography = biography;
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    
    
    
}