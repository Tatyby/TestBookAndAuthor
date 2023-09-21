package com.example.testbooksandauthor.controller;

import com.example.testbooksandauthor.domain.ErrorResponse;
import com.example.testbooksandauthor.exception.AuthorNotFoundException;
import com.example.testbooksandauthor.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AuthorNotFoundException.class)
    public ErrorResponse notFound(AuthorNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookNotFoundException.class)
    public ErrorResponse notFound(BookNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse error(RuntimeException exception) {
        return new ErrorResponse(exception.getMessage());
    }
}
