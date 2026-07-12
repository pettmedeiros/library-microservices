package com.peterson.bookservice.dto;

import java.util.UUID;

public record BookResponseDTO(
    UUID id,
    String title,
    String isbn,
    Integer publicationYear,
    UUID authorId
) {

    
    
}
