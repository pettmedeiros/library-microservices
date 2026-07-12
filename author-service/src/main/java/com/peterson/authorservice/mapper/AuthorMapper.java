package com.peterson.authorservice.mapper;

import com.peterson.authorservice.dto.AuthorRequestDTO;
import com.peterson.authorservice.dto.AuthorResponseDTO;
import com.peterson.authorservice.model.Author;

public class AuthorMapper {

     // Método que converte um AuthorRequestDTO em uma entidade Author.
    public static Author toEntity(AuthorRequestDTO dto){
        if (dto == null){
            return null;
        }

        Author author = new Author();
        author.setName(dto.name());
        author.setEmail(dto.email());
        author.setBiography(dto.biography());

        return author;
        
    }

    public static AuthorResponseDTO toDTO(Author author) {
        if (author == null){
             return null;
        }

        return new AuthorResponseDTO(
                author.getId(),
                author.getName(),
                author.getEmail(),
                author.getBiography()
        );
    }

    // Método para update (PUT) para atualizar autor
    public static void updateEntity(Author author, AuthorRequestDTO dto) {
        author.setName(dto.name());
        author.setEmail(dto.email());
        author.setBiography(dto.biography());
    }
    
}
