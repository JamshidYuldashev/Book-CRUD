package com.example.springbootcrud.book.service;

import com.example.springbootcrud.Author.repository.AuhtorRepository;
import com.example.springbootcrud.book.dto.BookCreateDto;
import com.example.springbootcrud.book.dto.BookResponseDto;
import com.example.springbootcrud.book.entity.BookEntity;
import com.example.springbootcrud.book.repository.BookRepository;
import org.springframework.beans.BeanUtils;
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

    @Override
    public ResponseEntity<?> createBook(BookCreateDto dto) {
        BookResponseDto bookResponseDto = new BookResponseDto();
//        Optional<AuthorEntity> optional = auhtorRepository.findById(dto.getAuthorId());
//        if (optional.isEmpty()){
//            return ResponseEntity.ok("This User not found!");
//        }
//        if (optional.get().getDeletedAt().equals(true)){
//            return ResponseEntity.ok("This Id: [" + dto.getAuthorId() +"] is disabled ");
//        }

        BookEntity bookEntity = new BookEntity();
//        bookEntity.setAuthorEntity(optional.get());
//        bookEntity.setAuthorId(dto.getAuthorId());
        bookEntity.setBookName(dto.getBookName());
        bookEntity.setPublish(dto.getPublish());
        bookEntity.setPrice(dto.getPrice());
        bookEntity.setDeletedAt(false);

        bookRepository.save(bookEntity);
        BeanUtils.copyProperties(bookEntity, bookResponseDto);
        return ResponseEntity.ok(bookResponseDto);
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<BookEntity> bookEntities = bookRepository.findAll();
        List<BookResponseDto> responseDtos = new ArrayList<>();
        bookEntities.forEach(bookEntity -> {
            BookResponseDto dto = new BookResponseDto();
            if (bookEntity.getDeletedAt().equals(false)){
                BeanUtils.copyProperties(bookEntity, dto);
                responseDtos.add(dto);
            }
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
}
