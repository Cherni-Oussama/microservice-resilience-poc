package com.example.product.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionResponse {
    private String message;
    private String errorDescription;
    private LocalDateTime timestamp;
}
