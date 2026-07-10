package com.peterson.authorservice.dto;


public record AuthorRequestDTO(
    
    String name,
    String email,
    String biography

) {}