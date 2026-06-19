package com.codebyarunyadav.book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.ArrayList;
import java.util.List;

import com.codebyarunyadav.book.DTO.AuthorDTO;
import com.codebyarunyadav.book.DTO.BookResponse;
import com.codebyarunyadav.book.DTO.RatingDTO;
import com.codebyarunyadav.book.client.AuthorClient;
import com.codebyarunyadav.book.client.RatingClient;
import com.codebyarunyadav.book.model.Book;
import com.codebyarunyadav.book.repo.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private AuthorClient authorClient;

    @Autowired
    private RatingClient ratingClient;

    @CircuitBreaker(name = "authorService", fallbackMethod = "getAuthorFallback")
    @Retry(name = "authorService")
    public AuthorDTO getAuthorDetails(Long authorId) {
        return authorClient.getAuthor(authorId);
    }

    @CircuitBreaker(name = "ratingService", fallbackMethod = "getRatingsFallback")
    @Retry(name = "ratingService")
    public List<RatingDTO> getRatingDetails(Long bookId) {
        return ratingClient.getRatings(bookId);
    }

    public BookResponse getBookDetails(Long id){
        Book book = bookRepo.findById(id).orElse(null);

        BookResponse res = new BookResponse();
        res.setBook(book);
        res.setAuthor(getAuthorDetails(book.getAuthorId()));
        res.setRatings(getRatingDetails(book.getId()));

        return res;
    }

    // Fallback method for Author Service
    public AuthorDTO getAuthorFallback(Long authorId, Exception ex) {
        System.out.println("Fallback triggered for Author Service: " + ex.getMessage());
        AuthorDTO fallbackAuthor = new AuthorDTO();
        fallbackAuthor.setId(authorId);
        fallbackAuthor.setName("Author Service Unavailable");
        fallbackAuthor.setBio("Currently unable to fetch author information");
        return fallbackAuthor;
    }

    // Fallback method for Rating Service
    public List<RatingDTO> getRatingsFallback(Long bookId, Exception ex) {
        System.out.println("Fallback triggered for Rating Service: " + ex.getMessage());
        return new ArrayList<>(); // Return empty list when rating service is unavailable
    }
}