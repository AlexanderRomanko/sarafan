package com.example.sarafan.service;

import com.example.sarafan.dto.UserDto;
import com.example.sarafan.entity.User;
import com.example.sarafan.exception.UserAlreadyExists;
import com.example.sarafan.mappers.UserMapper;
import com.example.sarafan.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto create(User user) throws UserAlreadyExists {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExists("User already exists");
        }
        userRepository.save(user);
        return UserMapper.INSTANCE.toDto(user);
    }

}
