package com.example.springbootcrud.author.service;

import com.example.springbootcrud.author.dto.AuthorCreateDto;
import com.example.springbootcrud.author.dto.AuthorResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService {
    ResponseEntity<?> createAuthor(AuthorCreateDto dto);

    ResponseEntity<?> update(AuthorResponseDto responseDto);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<?> addManyToMany(Long authID, Long bookID);

    ResponseEntity<?> getAuthId(long id);

    ResponseEntity<?> updatePartialAuthorEntity(Long id, String country);

    List<AuthorResponseDto> getAllAuthorsWithBooks();

    ResponseEntity<?> getAll();
}
