package com.example.springbootcrud.book.service;

import com.example.springbootcrud.book.dto.BookCreateDto;
import org.springframework.http.ResponseEntity;

public interface BooksService {
    ResponseEntity<?> createBook(BookCreateDto dto);

    ResponseEntity<?> getAll();

    ResponseEntity<?> delete(long id);

    ResponseEntity<?> createBookAndAuthor(BookCreateDto dto);
}
