package com.peterson.userservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;


import com.peterson.userservice.dto.UserRequestDTO;
import com.peterson.userservice.dto.UserResponseDTO;
import com.peterson.userservice.mapper.UserMapper;
import com.peterson.userservice.model.User;
import com.peterson.userservice.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO dto){
        User user = UserMapper.toEntity(dto);

        User userSaved = userRepository.save(user);

        return UserMapper.toDTO(userSaved);

    }

    @Transactional
    public List<UserResponseDTO> findAll(){
        List<User> users = userRepository.findAll();

        return users.stream().map(UserMapper::toDTO).toList();
    }
    
    @Transactional
    public UserResponseDTO findById(UUID id){
        User user = userRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Usúario não encontrado!"));

        return UserMapper.toDTO(user);
    }

    @Transactional
    public UserResponseDTO update(UUID id, UserRequestDTO dto){
        User user = userRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Usúario não encontrado!"));

            UserMapper.updateEntity(user, dto);

            User updateUser = userRepository.save(user);

            return UserMapper.toDTO(updateUser);
    }

    @Transactional
    public UserResponseDTO delete(UUID id){
        User user = userRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Usúario não encontrado!"));

            userRepository.delete(user);

        return UserMapper.toDTO(user);
    }
}
