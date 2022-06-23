package com.example.photoservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AlbumDto {

    private Long id;
    private String title;
    private List<ImageDto> images;
    private Long userId;

}