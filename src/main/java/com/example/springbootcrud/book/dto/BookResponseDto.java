package com.example.springbootcrud.book.dto;

import lombok.Data;
@Data
public class BookResponseDto {
    private Long id;
    private String bookName;
    private String publish;
    private int price;
}
