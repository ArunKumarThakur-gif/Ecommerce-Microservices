package com.user_service.dto;

import com.user_service.component.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ResponseUserDto {
    private long id;
    private String email;
    private List<Role> roles;
}
