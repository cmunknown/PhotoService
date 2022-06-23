package com.example.photoservice.service;

import com.example.photoservice.api.dao.IImageCategoryDao;
import com.example.photoservice.api.service.IImageCategoryService;
import com.example.photoservice.dto.ImageCategoryAllDto;
import com.example.photoservice.dto.ImageCategoryDto;
import com.example.photoservice.exception.IdNotFountException;
import com.example.photoservice.model.ImageCategory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageCategoryService implements IImageCategoryService {

    private final IImageCategoryDao imageCategoryDao;
    private final ModelMapper modelMapper;

    @Override
    public ImageCategoryDto add(ImageCategoryDto dto) {
        ImageCategory album = modelMapper.map(dto, ImageCategory.class);
        imageCategoryDao.save(album);
        return dto;
    }

    @Override
    public void delete(Long id) {
        imageCategoryDao.deleteById(id);
    }

    @Override
    public ImageCategoryAllDto getById(Long id) {
        return modelMapper.map(
                imageCategoryDao.findById(id).orElseThrow(() -> new IdNotFountException("ID not find")),
                ImageCategoryAllDto.class);
    }

    @Override
    public List<ImageCategoryDto> getAll() {
        List<ImageCategory> images = (List<ImageCategory>) imageCategoryDao.findAll();
        List<ImageCategoryDto> result = images
                .stream()
                .map(profile -> modelMapper.map(profile, ImageCategoryDto.class))
                .collect(Collectors.toList());
        return result;
    }
}
