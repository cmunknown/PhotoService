package com.example.photoservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ImageDto {

    private Long id;
    private List<ImageCategoryDto> categories;
    private String directory;
    private Long userId;
//    private Long categoryId;
//    private Long albumId;

}
