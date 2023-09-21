package com.example.testbooksandauthor.domain;

import com.example.testbooksandauthor.model.Author;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class BookResponse {
    private Integer bookId;
    private String bookName;
    private List<AuthorListResponse> authors;
}
