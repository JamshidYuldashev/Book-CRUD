package com.example.springbootcrud.service;

import com.example.springbootcrud.dto.createDto.BookCreateDto;
import org.springframework.http.ResponseEntity;

public interface BooksService {
    ResponseEntity<?> createBook(BookCreateDto dto);

    ResponseEntity<?> getAll();
}
