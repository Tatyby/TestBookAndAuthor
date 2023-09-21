package com.example.testbooksandauthor.service;

import com.example.testbooksandauthor.exception.BookNotFoundException;
import com.example.testbooksandauthor.model.Book;
import com.example.testbooksandauthor.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class WikiService {
    @Value("${wiki.api.url}")
    private String apiUrl;
    private final BookRepository bookRepository;
    private WebClient webClient;

    public WikiService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {
        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .build();
    }

    public Mono<Object> getBookFromWiki(Integer bookId) {
        final Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Книга по id: " + bookId + " не найдена"));
        String name = book.getBookName();
        return this.webClient
                .get()
                .uri("?action=query&list=search&srsearch={name}&format=json", name)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class);
    }
}
