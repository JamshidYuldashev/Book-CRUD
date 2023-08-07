package com.example.springbootcrud.book.dto;

import com.example.springbootcrud.book.entity.BookEntity;
import com.example.springbootcrud.book.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    @Autowired
    private BookRepository bookRepository;
    private final ModelMapper mapper;

    public BookMapper() {
        this.mapper = new ModelMapper();
    }
    public void test(){

    }

    public BookResponseDto bookEntityToResponseDto(BookEntity entity){
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(entity.getId());
        bookResponseDto.setBookName(entity.getBookName());
        bookResponseDto.setPublish(entity.getPublish());
        bookResponseDto.setPrice(entity.getPrice());
        return bookResponseDto;
    }

    public BookEntity bookCreateDtoToEntity(BookCreateDto dto){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookName(dto.getBookName());
        bookEntity.setPublish(dto.getPublish());
        bookEntity.setPrice(dto.getPrice());
        bookEntity.setDeletedAt(false);
        return bookRepository.save(bookEntity);
    }

}
