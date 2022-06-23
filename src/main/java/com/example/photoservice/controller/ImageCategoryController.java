package com.example.photoservice.controller;

import com.example.photoservice.api.service.IImageCategoryService;
import com.example.photoservice.dto.ImageCategoryAllDto;
import com.example.photoservice.dto.ImageCategoryDto;
import com.example.photoservice.dto.ImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/category")
public class ImageCategoryController {

    private final IImageCategoryService iImageCategoryService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ImageCategoryAllDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(iImageCategoryService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        iImageCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ImageCategoryDto> addCategory(@RequestBody ImageCategoryDto dto) {
        return new ResponseEntity<>(iImageCategoryService.add(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ImageCategoryDto>> getAll() {
        return new ResponseEntity<>(iImageCategoryService.getAll(), HttpStatus.OK);
    }
}