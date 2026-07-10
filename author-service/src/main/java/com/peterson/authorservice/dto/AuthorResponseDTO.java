package com.peterson.authorservice.dto;

import java.util.UUID;

public record AuthorResponseDTO(
        UUID id,
        String name,
        String email,
        String biography
) {}