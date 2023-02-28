package com.example.springbootcrud.service.ServiceImpl;

import com.example.springbootcrud.dto.AuthorResponseDto;
import com.example.springbootcrud.dto.createDto.AuthorCreateDto;
import com.example.springbootcrud.entity.AuthorEntity;
import com.example.springbootcrud.repository.AuhtorRepository;
import com.example.springbootcrud.service.AuthorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        entity.setDelete(false);
        auhtorRepository.save(entity);
        BeanUtils.copyProperties(entity, authorResponseDto);
        return authorResponseDto;
    }
    @Override
    public ResponseEntity<?> getAll() {
        List<AuthorEntity> authorEntities=auhtorRepository.findAll();
        List<AuthorResponseDto> responseDtos = new ArrayList<>();
        authorEntities.forEach(authorEntity -> {
            AuthorResponseDto dto = new AuthorResponseDto();
            if (authorEntity.getDelete().equals(false)){
                BeanUtils.copyProperties(authorEntity, dto);
                responseDtos.add(dto);
            }
        });
        return ResponseEntity.ok(responseDtos);
    }

    @Override
    public ResponseEntity<?> update(AuthorResponseDto responseDto) {
        Optional<AuthorEntity> authorEntity = auhtorRepository.findById(responseDto.getId());
        if (authorEntity.isEmpty()) {
            return ResponseEntity.ok("This User not found");
        }
        authorEntity.get().setAuthorName(responseDto.getAuthorName());
        authorEntity.get().setCountry(responseDto.getCountry());
        auhtorRepository.save(authorEntity.get());
        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<AuthorEntity> optional = auhtorRepository.findById(id);
        if (optional.isEmpty()){
            return ResponseEntity.ok(new NullPointerException());
        }
        optional.get().setDelete(true);
        optional.get().setDeleteDate(LocalDate.now());
        auhtorRepository.save(optional.get());
        return ResponseEntity.ok("Deleted id: " + id);
    }
}
