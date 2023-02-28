package com.example.springbootcrud.dto.createDto;

import lombok.Data;

@Data
public class BookCreateDto {
    private Long authorId;
    private String bookName;
    private String publish;
    private int price;
}
