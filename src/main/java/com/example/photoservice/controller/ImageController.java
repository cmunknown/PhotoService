package com.example.photoservice.controller;

import com.example.photoservice.api.service.IImageService;
import com.example.photoservice.dto.ImageDto;
import com.example.photoservice.dto.ImageDtoToAlbum;
import com.example.photoservice.exception.NotSupportedFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/images")
public class ImageController {

    private final IImageService imageService;


    @PostMapping(value = "/send/{userId}")
    public void addFile(HttpServletResponse response,
                                        @RequestParam("File") MultipartFile file,
                                        @PathVariable Long userId,
                                        @RequestParam List<Long> categories

    ) throws IOException {

        byte[] result = imageService.add(file, categories, userId);
        response.getOutputStream().write(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ImageDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(imageService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageService.delete(id);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping
//    public ResponseEntity<ImageDto> addImage(@RequestBody ImageDto dto) {
//        return new ResponseEntity<>(imageService.add(dto), HttpStatus.OK);
//    }

    @PostMapping(value = "/album")
    public ResponseEntity<ImageDtoToAlbum> addImageToAlbum(@RequestBody ImageDtoToAlbum dto) {
        return new ResponseEntity<>(imageService.addToAlbum(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ImageDto>> getAll() {
        return new ResponseEntity<>(imageService.getAll(), HttpStatus.OK);
    }
}