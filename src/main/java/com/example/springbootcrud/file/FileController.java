package com.example.springbootcrud.file;

import com.example.springbootcrud.book.entity.BookEntity;
import com.example.springbootcrud.message.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;


@RestController
//@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileStorageService storageService;

    @Operation(summary = "Upload File")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Upload file",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FileDB.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "404", description = "Books not found",
                    content = @Content(mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = MediaType.ALL_VALUE))})
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file")MultipartFile file){
        String message = "";
        try{
            storageService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
           return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @Operation(summary = "Get all files")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found files",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FileDB.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "404", description = "Files not found",
                    content = @Content(mediaType = MediaType.ALL_VALUE)) })
    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @Operation(summary = "Get file by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found file",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FileDB.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = MediaType.ALL_VALUE)),
            @ApiResponse(responseCode = "404", description = "File not found",
                    content = @Content(mediaType = MediaType.ALL_VALUE)) })
    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
}
