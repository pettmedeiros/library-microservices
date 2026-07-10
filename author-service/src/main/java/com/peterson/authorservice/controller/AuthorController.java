package com.peterson.authorservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peterson.authorservice.dto.AuthorRequestDTO;
import com.peterson.authorservice.dto.AuthorResponseDTO;
import com.peterson.authorservice.service.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    
    //controller depende sempre do service para executar as regras de negócio 
    AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> create (@Valid @RequestBody AuthorRequestDTO dto){

        AuthorResponseDTO response = authorService.createAuthor(dto);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>>findAll(){

        List<AuthorResponseDTO> authors = authorService.findAll();

        return ResponseEntity.ok(authors);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable UUID id){

        authorService.delete(id);
        
        return ResponseEntity.noContent().build();
    }
}
