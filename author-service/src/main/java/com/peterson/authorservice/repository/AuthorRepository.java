package com.peterson.authorservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peterson.authorservice.model.Author;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
    
}
