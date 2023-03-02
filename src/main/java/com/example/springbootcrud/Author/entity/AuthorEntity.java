package com.example.springbootcrud.Author.entity;

import com.example.springbootcrud.book.entity.BookEntity;
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
@Table(name = "authors")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Author ID", example = "1")
    private Long Id;

    @Schema(description = "Author's Country", example = "United States of America")
    private String country;

    @Schema(description = "Author's Name", example = "John Doe")
    private String authorName;
    @Schema(description = "If 'true', the Auhtor is disabled or Active ")
    private Boolean deletedAt =false;
    @Schema(description = "Date of Deleted", example = "2023-03-01")
    private LocalDate deleteDate;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "auhtor_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<BookEntity> bookAuthor = new HashSet<>();
}
