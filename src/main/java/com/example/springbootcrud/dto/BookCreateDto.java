package com.example.springbootcrud.dto;

import lombok.Data;

@Data
public class BookCreateDto {
    private String bookname;
    private String author;
    private int price;
}
