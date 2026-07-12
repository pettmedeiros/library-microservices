package com.peterson.bookservice.mapper;

import com.peterson.bookservice.dto.BookRequestDTO;
import com.peterson.bookservice.dto.BookResponseDTO;
import com.peterson.bookservice.model.Book;

public class BookMapper {

    public static Book toEntity(BookRequestDTO dto ){

        if (dto == null){
            return null;
        }

        Book book = new Book();
        book.setTitle(dto.title());
        book.setIsbn(dto.isbn());
        book.setPublicationYear(dto.publicationYear());
        book.setAuthorId(dto.authorId());

        return book;
    }
    
    public static BookResponseDTO toDTO(Book book){

        if (book == null){
            return null;
        }

        return new BookResponseDTO(
            book.getId(),
            book.getTitle(),
            book.getIsbn(),
            book.getPublicationYear(),
            book.getAuthorId()
        );

    }

    // Método para update (PUT) para atualizar autor
    public static void updateEntity(Book book, BookRequestDTO dto) {
        book.setTitle(dto.title());
        book.setIsbn(dto.isbn());
        book.setPublicationYear(dto.publicationYear());
        book.setAuthorId(dto.authorId());
    }
}
