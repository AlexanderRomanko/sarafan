package com.example.sarafan.controller;

import com.example.sarafan.dto.UserDto;
import com.example.sarafan.entity.User;
import com.example.sarafan.mappers.UserMapper;
import com.example.sarafan.repository.UserRepository;
import com.example.sarafan.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.create(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{username}")
    public UserDto show(@PathVariable String username) {
            return userService.getUser(username);
    }
}
