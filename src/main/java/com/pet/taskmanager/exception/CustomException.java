package com.pet.taskmanager.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    @Getter
    @Setter
    private HttpStatus status;

    public CustomException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public CustomException(String message) {
        super(message);
    }
}
