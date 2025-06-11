package com.bitsandbites.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CategoryAlreadyExitsException extends RuntimeException {
    public CategoryAlreadyExitsException(String message) {
        super(message);
    }
}
