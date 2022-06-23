package com.example.photoservice.dto;

import com.example.photoservice.model.Image;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
public class ImageCategoryAllDto {

    private Long id;
    private String title;
//    private List<ImageDto> imageCategory;
}
