package com.example.springbootcrud.book.service;

import com.example.springbootcrud.book.controller.repository.AuhtorRepository;
import com.example.springbootcrud.book.dto.BookCreateDto;
import com.example.springbootcrud.book.dto.BookMapper;
import com.example.springbootcrud.book.dto.BookPartialDto;
import com.example.springbootcrud.book.dto.BookResponseDto;
import com.example.springbootcrud.book.entity.BookEntity;
import com.example.springbootcrud.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BooksService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuhtorRepository auhtorRepository;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public ResponseEntity<?> createBook(BookCreateDto dto) {
        return ResponseEntity.ok(bookMapper.bookEntityToResponseDto(bookMapper.bookCreateDtoToEntity(dto)));
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<BookEntity> bookEntities = bookRepository.findAll();
        List<BookResponseDto> responseDtos = new ArrayList<>();
        bookEntities.forEach(bookEntity -> {
            if (bookEntity.getDeletedAt().equals(false))
                responseDtos.add(bookMapper.bookEntityToResponseDto(bookEntity));
        });
        return ResponseEntity.ok(responseDtos);
    }

    @Override
    public ResponseEntity<?> delete(long id) {
        Optional<BookEntity> optional=bookRepository.findById(id);
        if (optional.isEmpty())
            return ResponseEntity.ok("This User not found!");
        optional.get().setDeletedAt(true);
        optional.get().setDeleteDate(LocalDate.now());
        bookRepository.save(optional.get());
        return ResponseEntity.ok("Deleted id: " + id);
    }

    @Override
    public ResponseEntity<?> updatePartialBookEntity(Long id, BookPartialDto partialDto) {
        Optional<BookEntity> optional = bookRepository.findById(id);
        if (optional.isEmpty())
            return ResponseEntity.ok("This User not found!");
        optional.get().setBookName(partialDto.getBookName());
        optional.get().setPrice(partialDto.getPrice());
        return ResponseEntity.ok(bookRepository.save(optional.get()));
    }
}
