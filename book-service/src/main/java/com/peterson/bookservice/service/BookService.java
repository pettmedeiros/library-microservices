package com.peterson.bookservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.peterson.bookservice.dto.BookRequestDTO;
import com.peterson.bookservice.dto.BookResponseDTO;
import com.peterson.bookservice.mapper.BookMapper;
import com.peterson.bookservice.model.Book;
import com.peterson.bookservice.repository.BookRepository;

import jakarta.transaction.Transactional;

@Service
public class BookService {
    
    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public BookResponseDTO createBook (BookRequestDTO dto){
        Book book = BookMapper.toEntity(dto);
        Book savedBook = bookRepository.save(book);

        return BookMapper.toDTO(savedBook);
    }

    @Transactional
    public List<BookResponseDTO> findAll(){
        List<Book> books = bookRepository.findAll();

        return books.stream().map(BookMapper::toDTO).toList();
    }

    @Transactional
    public BookResponseDTO findById (UUID id){
        Book book = bookRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        return BookMapper.toDTO(book);
    }

    @Transactional
    public BookResponseDTO update(UUID id, BookRequestDTO dto){
        Book book = bookRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Livro não encontrado"));

            BookMapper.updateEntity(book, dto);

            Book updateBook = bookRepository.save(book);
            
            return BookMapper.toDTO(updateBook);
    }

    public BookResponseDTO delete(UUID id){

        Book book = bookRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Livro não encontrado"));

            bookRepository.delete(book);

            return BookMapper.toDTO(book);

    }

}
