package com.codebyarunyadav.book.controller;

import com.codebyarunyadav.book.DTO.BookResponse;
import com.codebyarunyadav.book.model.Book;
import com.codebyarunyadav.book.repo.BookRepository;
import com.codebyarunyadav.book.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookservice;
    
    @Autowired
    private BookRepository repo;

    @PostMapping
    public Book add(@RequestBody Book b){ return repo.save(b); }

   /*  @GetMapping("/{id}")
    public Map<String, Object> get(@PathVariable Long id){
        Book book = repo.findById(id).orElse(null);
        Map<String,Object> resp = new HashMap<>();
        resp.put("book", book);
        return resp;
    }
    */
    
    @GetMapping("/{id}")
    public BookResponse get(@PathVariable Long id){
        return bookservice.getBookDetails(id);
    }
}
