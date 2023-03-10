package com.example.springbootcrud.Author.dto;

import com.example.springbootcrud.Author.entity.AuthorEntity;
import com.example.springbootcrud.Author.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class AuthorMapper {
    @Autowired
    private AuthorRepository authorRepository;
    private final ModelMapper mapper;

    public AuthorMapper() {
        this.mapper = new ModelMapper();
    }

    public AuthorResponseDto authorEntityToResponseDto(AuthorEntity entity){
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setAuthorName(entity.getAuthorName());
        authorResponseDto.setBookAuthor(
                new HashSet<>(entity.getBookAuthor().stream().filter(
                        bookEntity -> bookEntity.getDeletedAt().equals(false)).toList()));
        authorResponseDto.setCountry(entity.getCountry());
        authorResponseDto.setId(entity.getId());
        return authorResponseDto;
    }

    public AuthorEntity authorCreateDtoToEntity(AuthorCreateDto dto){
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setCountry(dto.getCountry());
        authorEntity.setAuthorName(dto.getAuthorName());
        authorEntity.setDeletedAt(false);
        return authorRepository.save(authorEntity);
    }

}
