package com.example.springbootcrud.service;

import com.example.springbootcrud.dto.BookCreateDto;
import com.example.springbootcrud.dto.BookDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BooksService {
    BookDto addNewBook(BookCreateDto dto);
    ResponseEntity<List<BookDto>> getAllBooks();

    ResponseEntity<?> getDeletedOneBook(Long id);

    BookDto updateBook(BookDto dto);
}
