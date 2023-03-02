package com.example.springbootcrud.book.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
public class BookCreateDto {
    @Schema(description = "Name of Book", example = "One Thing")
    private String bookName;
    @Schema(description = "Publish of Book", example = "NY-Times")
    private String publish;
    @Schema(description = "Price of Book,  example = $15.2")
    private int price;
}
