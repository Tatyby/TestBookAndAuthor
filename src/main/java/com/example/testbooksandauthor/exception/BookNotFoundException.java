package com.example.testbooksandauthor.exception;


public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(final String msg) {
        super(msg);
    }
}
