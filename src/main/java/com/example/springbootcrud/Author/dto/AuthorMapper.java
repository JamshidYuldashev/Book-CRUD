package com.example.springbootcrud.Author.dto;

import com.example.springbootcrud.Author.entity.AuthorEntity;
import com.example.springbootcrud.Author.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;

@Component
public class AuthorMapper {
    @Autowired
    private AuthorRepository authorRepository;
    private final ModelMapper mapper;

    public AuthorMapper() {
        this.mapper = new ModelMapper();
    }

    public AuthorEntity authorCreateDto_To_Entity(AuthorCreateDto dto){
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setCountry(dto.getCountry());
        authorEntity.setAuthorName(dto.getAuthorName());
        authorEntity.setDeletedAt(false);
        authorEntity.setCreatedDate(LocalDate.now());
        return authorRepository.save(authorEntity);
    }

    public AuthorResponseDto returnAuthorsAndBooks(AuthorEntity entity){
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setAuthorName(entity.getAuthorName());
        authorResponseDto.setBookAuthor(
                new HashSet<>(entity.getBookAuthor().stream().filter(
                        bookEntity -> bookEntity.getDeletedAt().equals(false)).toList()));
        authorResponseDto.setCountry(entity.getCountry());
        authorResponseDto.setCreatedDate(LocalDate.now());
        authorResponseDto.setId(entity.getId());
        return authorResponseDto;
    }
    public AuthorResponseDto returnAuthorResponseDto(AuthorEntity entity){
        AuthorResponseDto responseDto = new AuthorResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setAuthorName(entity.getAuthorName());
        responseDto.setCountry(entity.getCountry());
        responseDto.setCreatedDate(entity.getCreatedDate());
        return responseDto;
    }
}
