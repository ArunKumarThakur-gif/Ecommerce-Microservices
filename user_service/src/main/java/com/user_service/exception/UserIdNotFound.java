package com.user_service.exception;

public class UserIdNotFound extends RuntimeException {
    public UserIdNotFound(String userIdNotFound) {
        super(userIdNotFound);
    }
}
