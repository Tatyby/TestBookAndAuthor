package com.example.testbooksandauthor.domain;

import lombok.Data;

import java.util.List;

@Data
public class BookRequest {
    private String bookName;
    private List<AuthorRequest> authors;
}
