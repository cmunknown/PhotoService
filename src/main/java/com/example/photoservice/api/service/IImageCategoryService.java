package com.example.photoservice.api.service;

import com.example.photoservice.dto.AlbumDto;
import com.example.photoservice.dto.ImageCategoryAllDto;
import com.example.photoservice.dto.ImageCategoryDto;
import com.example.photoservice.dto.ImageDto;

import java.util.List;

public interface IImageCategoryService {

    ImageCategoryDto add(ImageCategoryDto dto);

    void delete(Long id);

    ImageCategoryAllDto getById(Long id);

    List<ImageCategoryDto> getAll();
}
