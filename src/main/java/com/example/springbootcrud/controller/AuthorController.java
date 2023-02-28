package com.example.springbootcrud.controller;

import com.example.springbootcrud.dto.createDto.AuthorCreateDto;
import com.example.springbootcrud.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/create_author")
    public ResponseEntity<?> addAuthor(@RequestBody  AuthorCreateDto dto){
        return ResponseEntity.ok(authorService.createAuthor(dto));
    }



}
