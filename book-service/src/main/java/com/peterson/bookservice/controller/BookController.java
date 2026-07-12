package com.peterson.bookservice.controller;

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

import com.peterson.bookservice.dto.BookRequestDTO;
import com.peterson.bookservice.dto.BookResponseDTO;
import com.peterson.bookservice.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
         System.out.println(">>> BookController carregado!");
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> create (@Valid @RequestBody BookRequestDTO dto) {
        BookResponseDTO response = bookService.createBook(dto);

        return ResponseEntity.status(201).body(response);
    
    }
    
    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> findAll(){

        List<BookResponseDTO> books = bookService.findAll();

        return ResponseEntity.ok(books);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponseDTO> delete (@PathVariable UUID id){

        bookService.delete(id);
        
        return ResponseEntity.noContent().build();
    }
}
