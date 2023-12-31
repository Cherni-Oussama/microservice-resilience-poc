package com.example.Product.exception;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.UnsupportedMediaType;
import org.springframework.web.client.HttpServerErrorException.GatewayTimeout;

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

    @ExceptionHandler(GatewayTimeout.class)
    public ResponseEntity<ExceptionResponse> handleFileCouldNotBeSavedToBlobExceptionDueToTimeOut(GatewayTimeout exception) {
        ExceptionResponse body = ExceptionResponse.builder()
                .message(exception.getMessage())
                .errorDescription("Blob Storage is facing timout while uploading image")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(body);
    }

    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<ExceptionResponse> handleRequestNotPermittedDueToToManyRequests(RequestNotPermitted exception) {
        ExceptionResponse body = ExceptionResponse.builder()
                .message(exception.getMessage())
                .errorDescription("Maximum number of calls have been exceeded. Please retry in few seconds.")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(body);
    }

}
