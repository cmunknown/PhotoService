package com.example.photoservice.dto;

import com.example.photoservice.model.Album;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserDto {

    private Long id;
    private String username;
    private String password;
//    private List<AlbumDto> album;

}
