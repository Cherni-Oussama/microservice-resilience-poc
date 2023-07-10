package com.example.Product.exception;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.UnsupportedMediaType;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UnsupportedMediaType.class)
    public ResponseEntity<ExceptionResponse> handleFileCouldNotBeSavedToBlobExceptionDueToUnsupportedFormat(UnsupportedMediaType exception) {
        ExceptionResponse body = ExceptionResponse.builder()
                .errorDescription("It seems like tour file type is no longer supported by Blob Storage")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(body);
    }

    @ExceptionHandler(CallNotPermittedException.class)
    public ResponseEntity<ExceptionResponse> handleFileCouldNotBeSavedToBlobExceptionDueToCB(CallNotPermittedException exception) {
        ExceptionResponse body = ExceptionResponse.builder()
                .message(exception.getMessage())
                .errorDescription("Blob Storage is not responding correctly, Circuit breaker is Open Now")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

}
