package com.auth_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestUserDto {
    private String email;
    private String password;
}
