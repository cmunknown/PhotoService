package com.example.photoservice.service;

import com.example.photoservice.api.dao.IUserDao;
import com.example.photoservice.api.service.IUserService;
import com.example.photoservice.dto.UserDto;
import com.example.photoservice.exception.IdNotFountException;
import com.example.photoservice.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserDao userDao;
    private final ModelMapper modelMapper;

    @Override
    public UserDto add(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userDao.save(user);
        return userDto;
    }

    @Override
    public void delete(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public UserDto getById(Long id) {
        return modelMapper.map(
                userDao.findById(id).orElseThrow(() -> new IdNotFountException("ID not find")),
                UserDto.class);
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = (List<User>) userDao.findAll();
        List<UserDto> result = users
                .stream()
                .map(profile -> modelMapper.map(profile, UserDto.class))
                .collect(Collectors.toList());
        return result;
    }
}