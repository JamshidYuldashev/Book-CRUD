package com.example.springbootcrud.dto.createDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorDeleteDto {
    private Boolean delete=false;
    private LocalDate deleteDate;
}
