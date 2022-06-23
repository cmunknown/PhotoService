package com.example.photoservice.api.service;

import com.example.photoservice.dto.AlbumDto;
import com.example.photoservice.dto.ImageCategoryDto;
import com.example.photoservice.dto.ImageDto;

import java.util.List;

public interface IAlbumService {
    AlbumDto add(AlbumDto albumDto);

    void delete(Long id);

    AlbumDto getById(Long id);

    List<AlbumDto> getAll();
}
