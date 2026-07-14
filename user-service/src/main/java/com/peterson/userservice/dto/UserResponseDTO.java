package com.peterson.userservice.dto;

import java.util.UUID;

import com.peterson.userservice.model.enums.Role;

public record UserResponseDTO(
    UUID id,
    String name,
    String email,
    Role role
) {}
