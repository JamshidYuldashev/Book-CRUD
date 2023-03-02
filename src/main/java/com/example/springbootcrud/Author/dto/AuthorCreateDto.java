package com.example.springbootcrud.Author.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
public class AuthorCreateDto {

    @Schema(description = "Author's Country", example = "United States of America")
    private String country;
    @Schema(description = "Author's Name", example = "John Doe")
    private String authorName;
}
