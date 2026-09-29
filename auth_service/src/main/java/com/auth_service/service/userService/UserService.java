package com.auth_service.service.userService;

import com.auth_service.dto.LoginDto;
import com.auth_service.dto.RequestUserDto;
import com.auth_service.dto.ResponseUserDto;
import com.auth_service.enums.RoleType;

public interface UserService {
    ResponseUserDto signup(RequestUserDto user, RoleType type);
    LoginDto signin(RequestUserDto dto);

    ResponseUserDto getUserById(Long userId);
}
