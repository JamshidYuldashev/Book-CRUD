package com.example.springbootcrud.book.dto;

import lombok.Data;
@Data
public class BookCreateDto {
    private String bookName;
    private String publish;
    private int price;
}
