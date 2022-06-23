package com.example.photoservice.api.service;

import com.example.photoservice.dto.ImageDto;
import com.example.photoservice.dto.ImageDtoToAlbum;
import com.example.photoservice.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {


    byte[] add(MultipartFile file, List<Long> categories, Long useriD);

    ImageDtoToAlbum addToAlbum(ImageDtoToAlbum imageDto);

    void delete(Long id);

    ImageDto getById(Long id);

    List<ImageDto> getAll();

}
