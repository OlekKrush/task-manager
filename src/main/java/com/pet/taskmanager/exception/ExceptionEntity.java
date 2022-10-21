package com.pet.taskmanager.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ExceptionEntity(
        String message,
        HttpStatus httpStatus,
        ZonedDateTime zonedDateTime
) {
}
