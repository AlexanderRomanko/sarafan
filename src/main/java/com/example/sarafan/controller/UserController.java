package com.example.sarafan.controller;

import com.example.sarafan.entity.User;
import com.example.sarafan.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

//    private final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping
//    public String greeting() {
//        return "Hello, guest!";
//    }
//
//    @GetMapping("{username}")
//    public String show(@PathVariable String username) {
//            return String.valueOf(userService.getUser(username));
//    }
//
//    @PostMapping
//    public ResponseEntity create(@RequestBody User user) {
//        try {
//            return ResponseEntity.ok(userService.create(user));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

}
