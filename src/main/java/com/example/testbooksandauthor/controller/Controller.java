package com.example.testbooksandauthor.controller;

import com.example.testbooksandauthor.domain.AuthorsResponse;
import com.example.testbooksandauthor.domain.BookRequest;
import com.example.testbooksandauthor.domain.BookResponse;
import com.example.testbooksandauthor.service.AuthorService;
import com.example.testbooksandauthor.service.BookService;
import com.example.testbooksandauthor.service.WikiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class Controller {
    private final BookService bookService;
    private final AuthorService authorService;
    private final WikiService wikiService;

    @RequestMapping(value = "/books", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookResponse>> getAllBooks(@RequestParam Integer page,
                                                          @RequestParam Integer size,
                                                          @RequestParam(defaultValue = "bookName") String sortBy
    ) {
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.getAllBooks(pageRequest));

    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponse> getBookById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.getBookById(id));
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> addBook(@RequestBody BookRequest newBook) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.addBook(newBook));
    }

    @RequestMapping(value = "/authors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuthorsResponse>> getAllAuthors(@RequestParam Integer page,
                                                               @RequestParam Integer size,
                                                               @RequestParam(defaultValue = "authorFullName") String sortBy
    ) {
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return ResponseEntity.status(HttpStatus.OK)
                .body(authorService.getAllAuthors(pageRequest));
    }

    @RequestMapping(value = "books/{id}/wiki", method = RequestMethod.GET)
    public Mono<Object> getBookFromWiki(@PathVariable("id") Integer bookId) {
        return
                wikiService.getBookFromWiki(bookId);

    }

}
