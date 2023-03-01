package com.example.springbootcrud.Author.service;

import com.example.springbootcrud.Author.dto.AuthorResponseDto;
import com.example.springbootcrud.Author.dto.createDto.AuthorCreateDto;
import com.example.springbootcrud.Author.entity.AuthorEntity;
import com.example.springbootcrud.Author.repository.AuhtorRepository;
import com.example.springbootcrud.book.dto.BookResponseDto;
import com.example.springbootcrud.book.entity.BookEntity;
import com.example.springbootcrud.book.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuhtorRepository auhtorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public AuthorResponseDto createAuthor(AuthorCreateDto dto) {
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();

        AuthorEntity entity = new AuthorEntity();

        entity.setCountry(dto.getCountry());
        entity.setAuthorName(dto.getAuthorName());
        entity.setDeletedAt(false);
        auhtorRepository.save(entity);
        BeanUtils.copyProperties(entity, authorResponseDto);
        return authorResponseDto;
    }
    @Override
    public ResponseEntity<?> getAll() {
        List<AuthorEntity> authorEntities=auhtorRepository.findAll();
        List<AuthorResponseDto> responseDtos = new ArrayList<>();
        authorEntities.forEach(authorEntity -> {
            AuthorResponseDto dto = new AuthorResponseDto();
            if (authorEntity.getDeletedAt().equals(false)){
                BeanUtils.copyProperties(authorEntity, dto);
                responseDtos.add(dto);
            }
        });
        return ResponseEntity.ok(responseDtos);
    }

    @Override
    public ResponseEntity<?> update(AuthorResponseDto responseDto) {
        Optional<AuthorEntity> authorEntity = auhtorRepository.findById(responseDto.getId());
        if (authorEntity.isEmpty()) {
            return ResponseEntity.ok("This User not found");
        }
        authorEntity.get().setAuthorName(responseDto.getAuthorName());
        authorEntity.get().setCountry(responseDto.getCountry());
        auhtorRepository.save(authorEntity.get());
        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<AuthorEntity> optional = auhtorRepository.findById(id);
        if (optional.isEmpty()){
            return ResponseEntity.ok(new NullPointerException());
        }
        optional.get().setDeletedAt(true);
        optional.get().setDeleteDate(LocalDate.now());
        auhtorRepository.save(optional.get());
        return ResponseEntity.ok("Deleted id: " + id);
    }

    @Override
    public ResponseEntity<?> addManyToMany(Long authID, Long bookID) {
        Set<BookEntity> bookEntitySet = null;
        Optional<AuthorEntity> optionalAuthorEntity = auhtorRepository.findById(authID);
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(bookID);

        if (optionalAuthorEntity.get().getDeletedAt().equals(false) &&
                optionalBookEntity.get().getDeletedAt().equals(false)){

            bookEntitySet = optionalAuthorEntity.get().getBookAuthor();
            bookEntitySet.add(optionalBookEntity.get());


            auhtorRepository.save(optionalAuthorEntity.get());
        }

        AuthorResponseDto responseDto = new AuthorResponseDto();
        BeanUtils.copyProperties(optionalAuthorEntity.get(), responseDto);

        return ResponseEntity.ok(responseDto);
    }
}
