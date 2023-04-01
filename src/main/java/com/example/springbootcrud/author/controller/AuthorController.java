package com.example.springbootcrud.author.controller;

import com.example.springbootcrud.author.dto.AuthorCreateDto;
import com.example.springbootcrud.author.dto.AuthorResponseDto;
import com.example.springbootcrud.author.entity.AuthorEntity;
import com.example.springbootcrud.author.service.AuthorService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @Operation(summary = "Create Author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Add Author",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthorEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "404", description = "Author not found",
                    content = @Content(mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = MediaType.ALL_VALUE))})
    @PostMapping("/create_author")
    public ResponseEntity<?> addAuthor(@RequestBody  AuthorCreateDto dto){
        return ResponseEntity.ok(authorService.createAuthor(dto));
    }

    @Operation(summary = "Get all Authors with Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found authors",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthorEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content =@Content(mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "404", description = "Authors not found",
                    content = @Content(mediaType = MediaType.ALL_VALUE)) })
    @GetMapping("/authors_with_books")
    public List<AuthorResponseDto> getAllAuthorsWithBooks(){
        return authorService.getAllAuthorsWithBooks();
    }

    @Operation(summary = "Get all Authors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found authors",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthorEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content =@Content(mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "404", description = "Authors not found",
                    content = @Content(mediaType = MediaType.ALL_VALUE)) })
    @GetMapping("all_author")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(authorService.getAll());
    }

    @Operation(summary = "Update a author by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found author",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthorEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "404", description = "Author not found",
                    content = @Content(mediaType = MediaType.ALL_VALUE)) })
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody AuthorResponseDto responseDto){
        return ResponseEntity.ok(authorService.update(responseDto));
    }

    @Operation(summary = "Delete a author by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the author",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthorEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "404", description = "Author not found",
                    content = @Content(mediaType = MediaType.ALL_VALUE)) })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAuthor(
            @Parameter(description = "id of author to be searched")
            @PathVariable long id){
        return ResponseEntity.ok(authorService.delete(id));
    }


    @Operation(summary = "Connection Author with Book by their ids")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success !",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthorEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "404", description = "Author or Book not found",
                    content = @Content(mediaType = MediaType.ALL_VALUE)) })
    @JsonIgnore
    @PutMapping(("/{authID}/book/{bookID}"))
    public ResponseEntity<?>  addManyToMany(
            @Parameter(description = "id of author to be searched")
            @PathVariable Long authID,
            @Parameter(description = "id of book to be searched")
            @PathVariable Long bookID
    ){
        return ResponseEntity.ok(authorService.addManyToMany(authID, bookID));}

    @Operation(summary = "Get a author by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found authors",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthorEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "404", description = "Auhtors not found",
                    content = @Content(mediaType = MediaType.ALL_VALUE)) })
    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthId(
            @Parameter(description = "id of author to be searched")
            @PathVariable long id){
        return ResponseEntity.ok(authorService.getAuthId(id));
    }


    // https://techwithmaddy.com/difference-between-put-and-patch-request-in-spring-boot
    @Operation(summary = "Update a author by its id partial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found author and partial Updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthorEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "404", description = "Author not found",
                    content = @Content(mediaType = MediaType.ALL_VALUE)) })
    @PatchMapping("/update/{id}/{country}")
    public ResponseEntity<?> updatePartialAuthorEntity(@PathVariable Long id, @PathVariable String country){
        return ResponseEntity.ok(authorService.updatePartialAuthorEntity(id, country));
    }

}
