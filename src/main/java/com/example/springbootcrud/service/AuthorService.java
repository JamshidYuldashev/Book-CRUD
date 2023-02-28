package com.example.springbootcrud.service;

import com.example.springbootcrud.dto.AuthorResponseDto;
import com.example.springbootcrud.dto.createDto.AuthorCreateDto;
import org.springframework.http.ResponseEntity;

public interface AuthorService {
    AuthorResponseDto createAuthor(AuthorCreateDto dto);
}
