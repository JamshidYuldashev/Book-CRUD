package com.example.springbootcrud.dto;

import lombok.Data;

@Data
public class BookDto {
    private Long bookid;
    private String bookname;
    private String author;
    private int price;
}
