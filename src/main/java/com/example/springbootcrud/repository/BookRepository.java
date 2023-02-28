package com.example.springbootcrud.repository;

import com.example.springbootcrud.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
//    Optional<BookEntity>findByBookId(Long bookId);

}
