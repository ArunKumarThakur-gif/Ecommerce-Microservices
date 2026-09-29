package com.auth_service.exception;

public class UserAlreadyPresent extends RuntimeException {
    public UserAlreadyPresent(String userAlreadyRegister) {
        super(userAlreadyRegister);
    }
}
