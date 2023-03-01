package com.example.springbootcrud.Author.service;

import com.example.springbootcrud.Author.dto.AuthorResponseDto;
import com.example.springbootcrud.Author.dto.createDto.AuthorCreateDto;
import org.springframework.http.ResponseEntity;

public interface AuthorService {
    AuthorResponseDto createAuthor(AuthorCreateDto dto);
    ResponseEntity<?> getAll();

    ResponseEntity<?> update(AuthorResponseDto responseDto);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<?> addManyToMany(Long authID, Long bookID);

    ResponseEntity<?> getAuthId(long id);

}
