package com.example.springbootcrud.book.service;

import com.example.springbootcrud.book.dto.BookCreateDto;
import com.example.springbootcrud.book.dto.BookPartialDto;
import org.springframework.http.ResponseEntity;

public interface BooksService {
    ResponseEntity<?> createBook(BookCreateDto dto);

    ResponseEntity<?> getAll();

    ResponseEntity<?> delete(long id);

    ResponseEntity<?> updatePartialBookEntity(Long id, BookPartialDto partialDto);
}
