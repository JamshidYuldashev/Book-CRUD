package com.example.springbootcrud.dto;

import lombok.Data;
@Data
public class AuthorResponseDto {
    private Long id;
    private String country;
    private String authorName;
}
