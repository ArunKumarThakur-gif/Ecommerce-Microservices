package com.auth_service.dto;

import com.auth_service.entity.Role;
import com.auth_service.enums.UserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class ResponseUserDto {
    private long id;
    private String email;
    private UserStatus status;
    private List<Role> roles;
}
