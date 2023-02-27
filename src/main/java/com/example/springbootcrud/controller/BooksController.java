package com.example.springbootcrud.controller;

import com.example.springbootcrud.dto.BookCreateDto;
import com.example.springbootcrud.dto.BookDto;
import com.example.springbootcrud.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BooksController {

    @Autowired
    private BooksService booksService;

    @PostMapping("/new_book")
    ResponseEntity<?> addBook(@RequestBody BookCreateDto dto){
        BookDto bookDto = booksService.addNewBook(dto);
        return  ResponseEntity.ok(bookDto);
    }

    @GetMapping("/all")
    ResponseEntity<List<BookDto>> getAllBooks(){
        return booksService.getAllBooks();
    }

    @DeleteMapping("/deleted/{id}")
    ResponseEntity<?> deletedOneBook (@PathVariable Long id){
        return booksService.getDeletedOneBook(id);
    }

    @PutMapping("/update_book")
    ResponseEntity<?> updateBook (@RequestBody BookDto dto){
        BookDto bookDto = booksService.updateBook(dto);
        if(bookDto == null)
            return ResponseEntity.ok("There is no such information");
        return  ResponseEntity.ok("Done!");
    }




}
