package com.example.springbootcrud.Author.service;

import com.example.springbootcrud.Author.dto.AuthorCreateDto;
import com.example.springbootcrud.Author.dto.AuthorMapper;
import com.example.springbootcrud.Author.dto.AuthorResponseDto;
import com.example.springbootcrud.Author.entity.AuthorEntity;
import com.example.springbootcrud.Author.repository.AuthorRepository;
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
        return ResponseEntity.ok(authorMapper.authorEntityToResponseDto(authorMapper.authorCreateDtoToEntity(dto)));
    }
    @Override
    public ResponseEntity<?> getAll() {
        List<AuthorEntity> authorEntities= authorRepository.findAll();
        List<AuthorResponseDto> responseDtos = new ArrayList<>();
        authorEntities.forEach(authorEntity -> {;
            if (authorEntity.getDeletedAt().equals(false)){
                responseDtos.add(authorMapper.authorEntityToResponseDto(authorEntity));
            }
        });
        return ResponseEntity.ok(responseDtos);
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

        return ResponseEntity.ok(authorMapper.authorEntityToResponseDto(optionalAuthorEntity.get()));
    }

    @Override
    public ResponseEntity<?> getAuthId(long id) {
        Optional<AuthorEntity> optional = authorRepository.findById(id);
        if (optional.isEmpty())
            return ResponseEntity.ok("This User not found");
        if (optional.get().getDeletedAt().equals(true))
            return ResponseEntity.ok("This User deleted");

        return ResponseEntity.ok(authorMapper.authorEntityToResponseDto(optional.get()));
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
}
