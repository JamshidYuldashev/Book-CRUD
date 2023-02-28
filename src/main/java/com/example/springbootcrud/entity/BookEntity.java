package com.example.springbootcrud.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "author_Id", insertable = false, updatable = false)
    private Long authorId;

    @ManyToOne
    @JoinColumn(name="author_id")
    private AuthorEntity authorEntity;
    private String bookName;
    private String publish;
    private int price;

}