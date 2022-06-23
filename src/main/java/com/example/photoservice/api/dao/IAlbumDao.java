package com.example.photoservice.api.dao;

import com.example.photoservice.model.Album;
import org.springframework.data.repository.CrudRepository;

public interface IAlbumDao extends CrudRepository<Album, Long> {
}
