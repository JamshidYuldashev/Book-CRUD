package com.example.springbootcrud.repository;

import com.example.springbootcrud.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuhtorRepository extends JpaRepository<AuthorEntity, Long> {

}
