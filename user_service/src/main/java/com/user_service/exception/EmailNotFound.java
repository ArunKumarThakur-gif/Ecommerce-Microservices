package com.user_service.exception;

public class EmailNotFound extends RuntimeException {
    public EmailNotFound(String emailNotFound) {
        super(emailNotFound);
    }
}
