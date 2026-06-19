package com.codebyarunyadav.book.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codebyarunyadav.book.DTO.AuthorDTO;
import com.codebyarunyadav.book.DTO.RatingDTO;
import com.codebyarunyadav.book.client.AuthorClient;
import com.codebyarunyadav.book.client.RatingClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class BookExternalService {

    @Autowired
    private AuthorClient authorClient;

    @Autowired
    private RatingClient ratingClient;

    @CircuitBreaker(name = "authorService", fallbackMethod = "authorFallback")
    @Retry(name = "authorService")
    public AuthorDTO getAuthorDetails(Long authorId) {
        return authorClient.getAuthor(authorId);
    }

    public AuthorDTO authorFallback(Long authorId, Throwable ex) {
        AuthorDTO author = new AuthorDTO();
        author.setId(authorId);
        author.setName(null);
        author.setStatus("AUTHOR_SERVICE_TEMPORARILY_UNAVAILABLE");
        return author;
    }

    @CircuitBreaker(name = "ratingService", fallbackMethod = "ratingFallback")
    @Retry(name = "ratingService")
    public List<RatingDTO> getRatingDetails(Long bookId) {
        return ratingClient.getRatings(bookId);
    }

    public List<RatingDTO> ratingFallback(Long bookId, Throwable ex) {
        return Collections.emptyList();
    }
}