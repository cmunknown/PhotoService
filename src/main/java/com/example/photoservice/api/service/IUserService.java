package com.example.photoservice.api.service;

import com.example.photoservice.dto.UserDto;

import java.util.List;

public interface IUserService {

    UserDto add(UserDto userDto);

    void delete(Long id);

    UserDto getById(Long id);

    List<UserDto> getAll();
}
