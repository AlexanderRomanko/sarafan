package com.example.sarafan.mappers;

import com.example.sarafan.dto.UserDto;
import com.example.sarafan.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto toDto(User user);
    User toUser(UserDto userDto);
}
