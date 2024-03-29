package com.example.springbootcrud.author.service;

import com.example.springbootcrud.author.dto.AuthorCreateDto;
import com.example.springbootcrud.author.dto.AuthorMapper;
import com.example.springbootcrud.author.dto.AuthorResponseDto;
import com.example.springbootcrud.author.entity.AuthorEntity;
import com.example.springbootcrud.author.repository.AuthorRepository;
import com.example.springbootcrud.book.entity.BookEntity;
import com.example.springbootcrud.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public ResponseEntity<?> createAuthor(AuthorCreateDto dto) {
        return ResponseEntity.ok(authorMapper.returnAuthorResponseDto(authorMapper.authorCreateDto_To_Entity(dto)));
    }

    @Override
    public ResponseEntity<?> update(AuthorResponseDto responseDto) {
        Optional<AuthorEntity> authorEntity = authorRepository.findById(responseDto.getId());
        if (authorEntity.isEmpty()) {
            return ResponseEntity.ok("This User not found");
        }
        authorEntity.get().setAuthorName(responseDto.getAuthorName());
        authorEntity.get().setCountry(responseDto.getCountry());
        authorRepository.save(authorEntity.get());
        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<AuthorEntity> optional = authorRepository.findById(id);
        if (optional.isEmpty()){
            return ResponseEntity.ok("This User not found");
        }
        optional.get().setDeletedAt(true);
        optional.get().setDeleteDate(LocalDate.now());
        authorRepository.save(optional.get());
        return ResponseEntity.ok("Deleted id: " + id);
    }

    @Override
    public ResponseEntity<?> addManyToMany(Long authID, Long bookID) {
        Set<BookEntity> bookEntitySet = null;
        Optional<AuthorEntity> optionalAuthorEntity = authorRepository.findById(authID);
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(bookID);

        if (optionalAuthorEntity.get().getDeletedAt().equals(false) &&
                optionalBookEntity.get().getDeletedAt().equals(false)){

            bookEntitySet = optionalAuthorEntity.get().getBookAuthor();
            bookEntitySet.add(optionalBookEntity.get());

            authorRepository.save(optionalAuthorEntity.get());
        }

        return ResponseEntity.ok(authorMapper.returnAuthorsAndBooks(optionalAuthorEntity.get()));
    }

    @Override
    public ResponseEntity<?> getAuthId(long id) {
        Optional<AuthorEntity> optional = authorRepository.findById(id);
        if (optional.isEmpty())
            return ResponseEntity.ok("This User not found");
        if (optional.get().getDeletedAt().equals(true))
            return ResponseEntity.ok("This User deleted");

        return ResponseEntity.ok(authorMapper.returnAuthorResponseDto(optional.get()));
    }

    @Override
    public ResponseEntity<?> updatePartialAuthorEntity(Long id, String country) {
        Optional<AuthorEntity> authorEntity = authorRepository.findById(id);
        if (authorEntity.isEmpty()) {
            return ResponseEntity.ok("This User not found");
        }
        authorEntity.get().setCountry(country);
        return ResponseEntity.ok(authorRepository.save(authorEntity.get()));
    }

    @Override
    public List<AuthorResponseDto> getAllAuthorsWithBooks() {
        List<AuthorEntity> authorEntities= authorRepository.findAll();
        List<AuthorResponseDto> responseDto = new ArrayList<>();
        authorEntities.forEach(authorEntity -> {
            if (authorEntity.getDeletedAt().equals(false) && !authorEntity.getBookAuthor().isEmpty()){
                responseDto.add(authorMapper.returnAuthorsAndBooks(authorEntity));
            }
        });
        return responseDto;
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<AuthorEntity> entityList = authorRepository.findAll();
        List<AuthorResponseDto> responseDtoList = new ArrayList<>();
        entityList.forEach(authorEntity -> {
             responseDtoList.add(authorMapper.returnAuthorResponseDto(authorEntity));
        });
        return ResponseEntity.ok(responseDtoList);
    }
}
