package com.example.springbootcrud.Author.controller;

import com.example.springbootcrud.Author.dto.AuthorCreateDto;
import com.example.springbootcrud.Author.dto.AuthorResponseDto;
import com.example.springbootcrud.Author.service.AuthorService;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @PutMapping(("/{authID}/book/{bookID}"))
    public ResponseEntity<?>  addManyToMany(
            @PathVariable Long authID,
            @PathVariable Long bookID
    ){
        return ResponseEntity.ok(authorService.addManyToMany(authID, bookID));}

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthId(@PathVariable long id){
        return ResponseEntity.ok(authorService.getAuthId(id));
    }
}
