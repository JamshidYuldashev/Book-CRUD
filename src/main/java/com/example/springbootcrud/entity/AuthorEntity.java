package com.example.springbootcrud.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
@Entity
@Getter
@Setter
@Table(name = "authors")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToMany(mappedBy = "authorEntity")
    private Set<BookEntity> bookEntities;
    private String country;
    private String authorName;
    private Boolean delete=false;
    private LocalDate deleteDate;
}
