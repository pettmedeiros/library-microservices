package com.peterson.authorservice.service;


import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.peterson.authorservice.dto.AuthorRequestDTO;
import com.peterson.authorservice.dto.AuthorResponseDTO;
import com.peterson.authorservice.mapper.AuthorMapper;
import com.peterson.authorservice.model.Author;
import com.peterson.authorservice.repository.AuthorRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthorService {

    AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional //Anotação para gerenciar transações no banco de dados
    public AuthorResponseDTO createAuthor (AuthorRequestDTO dto){ 
        Author author = AuthorMapper.toEntity(dto);  //Converte DTO em entidade 
        Author savedAuthor = authorRepository.save(author);

        return AuthorMapper.toDTO(savedAuthor); //Converte entidade em DTO de resposta 
    }
    
    @Transactional
    public List<AuthorResponseDTO> findAll (){
        List<Author> authors = authorRepository.findAll(); //Cria uma lista com todos os autores 
        return authors.stream().map(AuthorMapper::toDTO).toList();

    }

    @Transactional
    public AuthorResponseDTO findById(UUID id){
        Author author = authorRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Autor não encontrado!"));

        return AuthorMapper.toDTO(author);
    }

    //Método criado para atualizar autor, como nome, email e biografia
    @Transactional
    public AuthorResponseDTO update(UUID id, AuthorRequestDTO dto){
        Author author = authorRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        // Atualiza os campos da entidade com os novos dados do DTO
        AuthorMapper.updateEntity(author, dto);

        Author updateAuthor = authorRepository.save(author);

        //Retorna o autor com atualizado como DTO
        return AuthorMapper.toDTO(updateAuthor);
    }

    @Transactional
    public AuthorResponseDTO delete(UUID id){
        Author author = authorRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Autor não encontrado!"));
        
        authorRepository.delete(author);

        return AuthorMapper.toDTO(author); // deleta o autor e devolve os dados dele 

        
    }
}
