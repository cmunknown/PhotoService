package com.example.photoservice.controller;

import com.example.photoservice.api.service.IAlbumService;
import com.example.photoservice.dto.AlbumDto;
import com.example.photoservice.dto.ImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/albums")
public class AlbumController {

    private final IAlbumService albumService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AlbumDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(albumService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<AlbumDto> addAlbum(@RequestBody AlbumDto dto) {
        return new ResponseEntity<>(albumService.add(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AlbumDto>> getAllAlbums() {
        return new ResponseEntity<>(albumService.getAll(), HttpStatus.OK);
    }
}