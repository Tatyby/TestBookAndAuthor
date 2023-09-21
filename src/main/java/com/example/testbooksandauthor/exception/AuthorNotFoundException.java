package com.example.testbooksandauthor.exception;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(String msg){
        super(msg);
    }
}
