package com.example.photoservice.service;

import com.example.photoservice.api.dao.IAlbumDao;
import com.example.photoservice.api.dao.IUserDao;
import com.example.photoservice.api.service.IAlbumService;
import com.example.photoservice.dto.AlbumDto;
import com.example.photoservice.exception.IdNotFountException;
import com.example.photoservice.model.Album;
import com.example.photoservice.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlbumService implements IAlbumService {

    private final IAlbumDao albumDao;
    private final ModelMapper modelMapper;
    private final IUserDao userDao;


    @Override
    public AlbumDto add(AlbumDto dto) {
        Album album = modelMapper.map(dto, Album.class);

        User user = userDao.findById(dto.getUserId()).orElseThrow(() -> new IdNotFountException("User not found not find"));
        album.setUserAlbum(user);

        albumDao.save(album);
        return dto;
    }

    @Override
    public void delete(Long id) {
        albumDao.deleteById(id);
    }

    @Override
    public AlbumDto getById(Long id) {
        return modelMapper.map(
                albumDao.findById(id).orElseThrow(() -> new IdNotFountException("ID not find")),
                AlbumDto.class);
    }

    @Override
    public List<AlbumDto> getAll() {
        List<Album> albums = (List<Album>) albumDao.findAll();
        List<AlbumDto> result = albums
                .stream()
                .map(profile -> modelMapper.map(profile, AlbumDto.class))
                .collect(Collectors.toList());
        return result;
    }
}
