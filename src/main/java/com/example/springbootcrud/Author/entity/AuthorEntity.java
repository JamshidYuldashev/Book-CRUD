package com.example.springbootcrud.Author.entity;

import com.example.springbootcrud.book.entity.BookEntity;
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
    private Long Id;

//    @OneToMany(mappedBy = "authorEntity")
//    private Set<BookEntity> bookEntities;
    private String country;
    private String authorName;
    private Boolean deletedAt =false;
    private LocalDate deleteDate;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "auhtor_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<BookEntity> bookAuthor = new HashSet<>();
}
