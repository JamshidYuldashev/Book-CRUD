package com.example.springbootcrud.Author.dto;

import com.example.springbootcrud.book.entity.BookEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class AuthorResponseDto {

    @Schema(description = "Author ID", example = "1")
    private Long id;

    @Schema(description = "Author's Country", example = "United States of America")
    private String country;

    @Schema(description = "Author's Name", example = "John Doe")
    private String authorName;

    @Schema(description = "Date of Created", example = "2023-03-01")
    private LocalDate createdDate;

    @Schema(description = "Book class")
    private Set<BookEntity> bookAuthor = new HashSet<>();
}
