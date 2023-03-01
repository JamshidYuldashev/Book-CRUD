package com.example.springbootcrud.book.controller;

import com.example.springbootcrud.book.dto.BookCreateDto;
import com.example.springbootcrud.book.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/book")
public class BooksController {
    @Autowired
    private BooksService booksService;

    @PostMapping("/create_book")
    public ResponseEntity<?> createBook(@RequestBody BookCreateDto dto){
        return ResponseEntity.ok(booksService.createBook(dto));
    }
    @GetMapping("/all_book")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(booksService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deteleBook(@PathVariable long id){
        return ResponseEntity.ok(booksService.delete(id));
    }

}
