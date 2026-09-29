package com.product_service.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@Builder
public class ErrorResponse {
    private String errorCode;
    private String message;
    private String path;
    private int status;
    private Instant timestamp;
    private String correlationId;
}
