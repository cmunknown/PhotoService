package com.example.photoservice.controller;

import com.example.photoservice.api.service.IUserService;
import com.example.photoservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final IUserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto dto) {
        return new ResponseEntity<>(userService.add(dto), HttpStatus.OK);
    }

}