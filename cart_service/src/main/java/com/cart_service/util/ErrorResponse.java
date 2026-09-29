package com.cart_service.util;

import com.cart_service.enums.ErrorCode;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    @Enumerated(EnumType.STRING)
    private ErrorCode errorCode;
    private String message;
    private String path;
    private int status;
    private Instant timestamp;
    private String correlationId;
}
