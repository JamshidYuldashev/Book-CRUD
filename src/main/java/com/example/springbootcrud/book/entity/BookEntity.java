package com.example.springbootcrud.book.entity;

import com.example.springbootcrud.author.entity.AuthorEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Book ID", example = "1")
    private Long Id;
    @Schema(description = "Name of Book", example = "One Thing")
    private String bookName;
    @Schema(description = "Publish of Book", example = "NY-Times")
    private String publish;
    @Schema(description = "Price of Book,  example = $15.2")
    private int price;
    @Schema(description = "If 'true', the book is disabled or Active ")
    private Boolean deletedAt = false;
    @Schema(description = "Date of Deleted", example = "2023-03-01")
    private LocalDate deleteDate;

    @JsonIgnore
    @ManyToMany(mappedBy = "bookAuthor")
    private Set<AuthorEntity> authorEntities = new HashSet<>();
}