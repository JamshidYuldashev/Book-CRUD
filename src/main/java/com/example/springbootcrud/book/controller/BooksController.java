package com.example.springbootcrud.book.controller;

import com.example.springbootcrud.book.dto.BookCreateDto;
import com.example.springbootcrud.book.entity.BookEntity;
import com.example.springbootcrud.book.service.BooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/book")
public class BooksController {
    @Autowired
    private BooksService booksService;


    @Operation(summary = "Create Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Add book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Books not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @PostMapping("/create_book")
    public ResponseEntity<?> createBook(@RequestBody BookCreateDto dto){
        return ResponseEntity.ok(booksService.createBook(dto));
    }

    @Operation(summary = "Get all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found books",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Books not found",
                    content = @Content) })
    @GetMapping("/all_book")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(booksService.getAll());
    }

    @Operation(summary = "Delete a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deteleBook(
            @Parameter(description = "id of book to be searched")
            @PathVariable long id){
        return ResponseEntity.ok(booksService.delete(id));
    }

}
