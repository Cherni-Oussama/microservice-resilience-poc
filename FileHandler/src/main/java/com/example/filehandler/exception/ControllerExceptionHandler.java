package com.example.filehandler.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(FileCouldNotBeSavedToBlobException.class)
    public ResponseEntity<ExceptionResponse> handleFileCouldNotBeSavedToBlobException(FileCouldNotBeSavedToBlobException exception) {
        ExceptionResponse body = ExceptionResponse.builder()
                .message(exception.getMessage())
                .errorDescription(exception.getErrorDescription())
                .error(exception.getError())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(body, exception.getHttpStatus());
    }

}
