package com.example.springbootcrud.service.ServiceImpl;

import com.example.springbootcrud.dto.BookResponseDto;
import com.example.springbootcrud.dto.createDto.BookCreateDto;
import com.example.springbootcrud.entity.AuthorEntity;
import com.example.springbootcrud.entity.BookEntity;
import com.example.springbootcrud.repository.AuhtorRepository;
import com.example.springbootcrud.repository.BookRepository;
import com.example.springbootcrud.service.BooksService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        Optional<AuthorEntity> optional = auhtorRepository.findById(dto.getAuthorId());
        if (optional.isEmpty()){
            return ResponseEntity.ok("This User not found!");
        }

        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthorEntity(optional.get());
        bookEntity.setAuthorId(dto.getAuthorId());
        bookEntity.setBookName(dto.getBookName());
        bookEntity.setPublish(dto.getPublish());
        bookEntity.setPrice(dto.getPrice());

        bookRepository.save(bookEntity);
        BeanUtils.copyProperties(bookEntity, bookResponseDto);
        return ResponseEntity.ok(bookResponseDto);
    }
}
