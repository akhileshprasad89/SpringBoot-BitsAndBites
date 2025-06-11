package com.bitsandbites.exception;

import com.bitsandbites.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CategoryAlreadyExitsException.class)
    public ResponseEntity<ExceptionResponseDto> categoryAlredyExits(CategoryAlreadyExitsException ex, WebRequest webRequest) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.setApiPath(webRequest.getDescription(false));
        exceptionResponseDto.setErrorMessage(ex.getMessage());
        exceptionResponseDto.setStatusCode(HttpStatus.CONFLICT);
        exceptionResponseDto.setErrorTime(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> categoryNotFound(CategoryNotFoundException ex, WebRequest webRequest) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.setApiPath(webRequest.getDescription(false));
        exceptionResponseDto.setErrorMessage(ex.getMessage());
        exceptionResponseDto.setStatusCode(HttpStatus.NOT_FOUND);
        exceptionResponseDto.setErrorTime(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> categoryNotFound(Exception ex, WebRequest webRequest) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.setApiPath(webRequest.getDescription(false));
        exceptionResponseDto.setErrorMessage(ex.getMessage());
        exceptionResponseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        exceptionResponseDto.setErrorTime(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
