package com.pet.taskmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ExceptionEntity> handlerException(CustomException e) {
        HttpStatus status = e.getStatus() != null ? e.getStatus() : HttpStatus.BAD_REQUEST;
        ExceptionEntity exceptionEntity = new ExceptionEntity(
                e.getMessage(),
                status,
                ZonedDateTime.now(ZoneId.systemDefault())
        );
        return new ResponseEntity<>(exceptionEntity, status);
    }

}
