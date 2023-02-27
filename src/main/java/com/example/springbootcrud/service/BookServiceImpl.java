package com.example.springbootcrud.service;

import com.example.springbootcrud.dto.BookCreateDto;
import com.example.springbootcrud.dto.BookDto;
import com.example.springbootcrud.entity.Books;
import com.example.springbootcrud.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BooksService{
    @Autowired
    private BookRepository bookRepository;
    @Override
    public BookDto addNewBook(BookCreateDto dto) {
        Books books= new Books(dto);
        bookRepository.save(books);
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(books, bookDto);
        return bookDto;
    }

    @Override
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<Books> entity=bookRepository.findAll();
        List<BookDto> bookDtos =new ArrayList<>();
        entity.forEach(books -> {
            BookDto bookDto=new BookDto();
            BeanUtils.copyProperties(books, bookDto);
            bookDtos.add(bookDto);
        });
        return ResponseEntity.ok(bookDtos);
    }

    @Override
    public ResponseEntity<?> getDeletedOneBook(Long id) {
        Optional<Books> optional=bookRepository.findById(id);
        optional.ifPresent(books -> bookRepository.delete(books));
        return ResponseEntity.ok("Deleted");
    }

    @Override
    public BookDto updateBook(BookDto dto) {
        Optional<Books> optional =bookRepository.findById(dto.getBookid());
        if (optional.isEmpty()){
            return null;
        }
        optional.get().setBookname(dto.getBookname());
        optional.get().setAuthor(dto.getAuthor());
        optional.get().setPrice(dto.getPrice());
        bookRepository.save(optional.get());
        return dto;
    }
}
