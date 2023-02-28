package com.example.springbootcrud.controller;

import com.example.springbootcrud.dto.createDto.AuthorCreateDto;
import com.example.springbootcrud.dto.createDto.BookCreateDto;
import com.example.springbootcrud.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.crossstore.ChangeSetPersister.*;

@RestController
@RequestMapping("/book")
public class BooksController {

    @Autowired
    private BooksService booksService;

    @PostMapping("/create_book")
    public ResponseEntity<?> createBook(@RequestBody BookCreateDto dto){
        return ResponseEntity.ok(booksService.createBook(dto));
    }


}
