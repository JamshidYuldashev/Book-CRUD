package com.example.springbootcrud.service.ServiceImpl;

import com.example.springbootcrud.dto.AuthorResponseDto;
import com.example.springbootcrud.dto.createDto.AuthorCreateDto;
import com.example.springbootcrud.entity.AuthorEntity;
import com.example.springbootcrud.repository.AuhtorRepository;
import com.example.springbootcrud.service.AuthorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuhtorRepository auhtorRepository;

    @Override
    public AuthorResponseDto createAuthor(AuthorCreateDto dto) {
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();

        AuthorEntity entity = new AuthorEntity();

        entity.setCountry(dto.getCountry());
        entity.setAuthorName(dto.getAuthorName());
        auhtorRepository.save(entity);
        BeanUtils.copyProperties(entity, authorResponseDto);
        return authorResponseDto;
    }
}
