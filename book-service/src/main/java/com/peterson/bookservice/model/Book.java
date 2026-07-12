package com.peterson.bookservice.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(nullable = false, length =  150)
    @NotBlank(message = "Título obrigatório")
    String title;

    @Column(nullable = false, length =  100)
    @NotBlank(message = "ISBN obrigatório")
    String isbn;

    @Column(nullable = false)
    Integer publicationYear;

    UUID authorId;

    public Book() {
    }

    public Book(UUID id, @NotBlank(message = "Título obrigatório") String title,
            @NotBlank(message = "ISBN obrigatório") String isbn, Integer publicationYear, UUID authorId) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.authorId = authorId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    

}
