package com.peterson.userservice.mapper;

import com.peterson.userservice.dto.UserRequestDTO;
import com.peterson.userservice.dto.UserResponseDTO;
import com.peterson.userservice.model.User;

public class UserMapper {
    
    public static User toEntity(UserRequestDTO dto){

        if(dto == null){
            return null;
        }

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setRole(dto.role());

        return user;
    }

    public static UserResponseDTO toDTO(User user){
        if (user == null){
            return null;
        }

        return new UserResponseDTO(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getRole()
        );
    }

     // Método para update (PUT) para atualizar autor
    public static void updateEntity(User user, UserRequestDTO dto) {
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setRole(dto.role());
    }
}
