package com.aoa.springwebservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RestControllerAdvice
@RequestMapping("/api")
public class DefaultRestControllerAdvice {

    @ExceptionHandler({RuntimeException.class, EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String defaultExceptionHandler(RuntimeException exception) {
        exception.printStackTrace();
        return exception.getMessage();
    }

    @ExceptionHandler(FileStorageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String fileStorageExceptionHandler(FileStorageException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public String unAuthorized(UnAuthorizedException exception) {
        log.debug("unAuthorized : {}", exception);
        return exception.getMessage();
    }
}