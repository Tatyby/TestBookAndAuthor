package com.example.testbooksandauthor.service;

import com.example.testbooksandauthor.domain.AuthorListResponse;
import com.example.testbooksandauthor.domain.AuthorRequest;
import com.example.testbooksandauthor.domain.BookRequest;
import com.example.testbooksandauthor.domain.BookResponse;
import com.example.testbooksandauthor.exception.BookNotFoundException;
import com.example.testbooksandauthor.model.Author;
import com.example.testbooksandauthor.model.Book;
import com.example.testbooksandauthor.model.BookAuthorRelation;
import com.example.testbooksandauthor.repository.AuthorRepository;
import com.example.testbooksandauthor.repository.BookAuthorRelationRepository;
import com.example.testbooksandauthor.repository.BookRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookAuthorRelationRepository bookAuthorRelationRepository;

    @Transactional(readOnly = true)
    public List<BookResponse> getAllBooks(PageRequest pageRequest) {
        return bookRepository.findAll(pageRequest)
                .stream()
                .map(this::buildBookResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BookResponse getBookById(final Integer id) {
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Книга по id: " + id + " не найдена"));
        return buildBookResponse(book);
    }

    public Integer addBook(@NotNull BookRequest newBook) {
        Book book = new Book()
                .setBookName(newBook.getBookName());
        bookRepository.save(book);
        List<Author> authors = new ArrayList<>();
        for (AuthorRequest authorRequest : newBook.getAuthors()) {
            Author author = authorRepository.findByAuthorFullName(authorRequest.getAuthorFullName());
            if (author == null) {
                author = new Author();
                author.setAuthorFullName(authorRequest.getAuthorFullName());
                authorRepository.save(author);
            }
            BookAuthorRelation bookAuthorRelation = new BookAuthorRelation()
                    .setAuthor(author)
                    .setBook(book);
            bookAuthorRelationRepository.save(bookAuthorRelation);
            authors.add(author);
        }

        return book.getBookId();
    }


    private BookResponse buildBookResponse(Book book) {
        return new BookResponse()
                .setBookId(book.getBookId())
                .setBookName(book.getBookName())
                .setAuthors(buildAuthorResponse(book.getAuthor()));

    }

    private List<AuthorListResponse> buildAuthorResponse(List<Author> authors) {
        return authors.stream()
                .map(author -> {
                    AuthorListResponse authorListResponse = new AuthorListResponse();
                    authorListResponse.setAuthorId(author.getAuthorId());
                    authorListResponse.setAuthorFullName(author.getAuthorFullName());
                    return authorListResponse;
                })
                .collect(Collectors.toList());
    }


}
