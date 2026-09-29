package com.user_service.exception;

public class ClaimNotFoundException extends RuntimeException {
    public ClaimNotFoundException(String claimNotFound) {
        super(claimNotFound);
    }
}
