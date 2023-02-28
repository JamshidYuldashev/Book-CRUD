package com.example.springbootcrud.controller;

import com.example.springbootcrud.dto.AuthorResponseDto;
import com.example.springbootcrud.dto.createDto.AuthorCreateDto;
import com.example.springbootcrud.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/create_author")
    public ResponseEntity<?> addAuthor(@RequestBody  AuthorCreateDto dto){
        return ResponseEntity.ok(authorService.createAuthor(dto));
    }
    @GetMapping("/all_author")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(authorService.getAll());
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody AuthorResponseDto responseDto){
        return ResponseEntity.ok(authorService.update(responseDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deteleAuthor(@PathVariable long id){
        return ResponseEntity.ok(authorService.delete(id));
    }


}
