package com.example.photoservice.api.dao;

import com.example.photoservice.model.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<User, Long> {
}
