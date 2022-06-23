package com.example.photoservice.api.dao;

import com.example.photoservice.model.Image;
import org.springframework.data.repository.CrudRepository;

public interface IImageDao extends CrudRepository<Image, Long> {
}
