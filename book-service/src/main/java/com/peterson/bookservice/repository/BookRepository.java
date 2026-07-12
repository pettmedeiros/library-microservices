package com.peterson.bookservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peterson.bookservice.model.Book;

public interface BookRepository extends JpaRepository<Book, UUID> {
    
}
