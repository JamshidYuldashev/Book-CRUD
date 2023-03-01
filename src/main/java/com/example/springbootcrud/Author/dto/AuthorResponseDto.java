package com.example.springbootcrud.Author.dto;

import com.example.springbootcrud.book.entity.BookEntity;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class AuthorResponseDto {
    private Long id;
    private String country;
    private String authorName;
    private Set<BookEntity> bookAuthor = new HashSet<>();
}
