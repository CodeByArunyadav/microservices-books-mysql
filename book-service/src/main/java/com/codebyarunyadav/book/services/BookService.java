package com.codebyarunyadav.book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codebyarunyadav.book.DTO.BookResponse;
import com.codebyarunyadav.book.model.Book;
import com.codebyarunyadav.book.repo.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private BookExternalService bookExternalService;

    public BookResponse getBookDetails(Long id) {

        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        BookResponse res = new BookResponse();
        res.setBook(book);
        res.setAuthor(bookExternalService.getAuthorDetails(book.getAuthorId()));
        res.setRatings(bookExternalService.getRatingDetails(book.getId()));

        return res;
    }
}