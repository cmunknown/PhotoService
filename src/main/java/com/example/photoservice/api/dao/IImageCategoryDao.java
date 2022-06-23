package com.example.photoservice.api.dao;

import com.example.photoservice.model.ImageCategory;
import org.springframework.data.repository.CrudRepository;

public interface IImageCategoryDao extends CrudRepository<ImageCategory, Long> {
}
