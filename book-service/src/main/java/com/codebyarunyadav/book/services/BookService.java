package com.codebyarunyadav.book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codebyarunyadav.book.DTO.BookResponse;
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

    public BookResponse getBookDetails(Long id){
        Book book = bookRepo.findById(id).orElse(null);

        BookResponse res = new BookResponse();
        res.setBook(book);
        res.setAuthor(authorClient.getAuthor(book.getAuthorId()));
        res.setRatings(ratingClient.getRatings(book.getId()));

        return res;
    }
}