package ru.vtb.slepenkov.datamanager.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(NotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NoSuchColumnException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String NoSuchColumnHandler(NoSuchColumnException ex) {
        return ex.getMessage();
    }
}
