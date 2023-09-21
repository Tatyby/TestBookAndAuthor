package com.example.testbooksandauthor.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthorListResponse {
    private Integer authorId;
    private String authorFullName;
}
