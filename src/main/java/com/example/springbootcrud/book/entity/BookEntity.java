package com.example.springbootcrud.book.entity;

import com.example.springbootcrud.Author.entity.AuthorEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long Id;

    private String bookName;
    private String publish;
    private int price;
    private Boolean deletedAt = false;
    private LocalDate deleteDate;

    @JsonIgnore
    @ManyToMany(mappedBy = "bookAuthor")
    private Set<AuthorEntity> authorEntities = new HashSet<>();
}