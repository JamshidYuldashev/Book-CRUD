package com.example.springbootcrud.book.controller.repository;

import com.example.springbootcrud.Author.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuhtorRepository extends JpaRepository<AuthorEntity, Long> {

}
