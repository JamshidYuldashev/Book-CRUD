package com.example.springbootcrud.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository repository;

    public void store(MultipartFile file) throws Exception{
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
        repository.save(FileDB);
    }

    public FileDB getFile(String id) {
        return repository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return repository.findAll().stream();
    }
}
