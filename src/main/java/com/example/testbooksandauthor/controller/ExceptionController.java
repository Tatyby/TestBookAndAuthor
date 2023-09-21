package com.example.testbooksandauthor.controller;

import com.example.testbooksandauthor.domain.ErrorResponse;
import com.example.testbooksandauthor.exception.AuthorNotFoundException;
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

}
