package com.auth_service.mapper;

import com.auth_service.dto.ResponseUserDto;
import com.auth_service.entity.User;

public class UserMapper {
    public static ResponseUserDto toResponseDto(User user) {
        return ResponseUserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .status(user.getStatus())
                .roles(user.getRoles())
                .build();
    }
}
