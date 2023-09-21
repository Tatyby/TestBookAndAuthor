package com.example.testbooksandauthor.service;

import com.example.testbooksandauthor.domain.AuthorsResponse;
import com.example.testbooksandauthor.model.Author;
import com.example.testbooksandauthor.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public List<AuthorsResponse> getAllAuthors(PageRequest pageRequest) {
        return authorRepository.findAll(pageRequest)
                .stream()
                .map(this::buildAuthorsResponse)
                .collect(Collectors.toList());

    }

    private AuthorsResponse buildAuthorsResponse(Author author) {
        return new AuthorsResponse()
                .setAuthorId(author.getAuthorId())
                .setAuthorFullName(author.getAuthorFullName())
                .setBooksQty(author.getBooksQty());

    }
}
