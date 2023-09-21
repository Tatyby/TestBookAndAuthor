package com.example.testbooksandauthor.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthorsResponse {
    private Integer authorId;
    private String authorFullName;
    private Integer booksQty;
}
