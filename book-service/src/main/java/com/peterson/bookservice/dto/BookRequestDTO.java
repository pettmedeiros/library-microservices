package com.peterson.bookservice.dto;

import java.util.UUID;

public record BookRequestDTO(
    String title,
    String isbn,
    Integer publicationYear,
    UUID authorId
) {

}
