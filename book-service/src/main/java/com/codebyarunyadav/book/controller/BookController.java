package com.codebyarunyadav.book.controller;

import com.codebyarunyadav.book.DTO.AuditEvent;
import com.codebyarunyadav.book.DTO.BookResponse;
import com.codebyarunyadav.book.kafka.AuditProducer;
import com.codebyarunyadav.book.model.Book;
import com.codebyarunyadav.book.repo.BookRepository;
import com.codebyarunyadav.book.services.BookService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// Note: Add your AuditEvent and AuditProducer imports based on your project structure
// import com.example.audit.event.AuditEvent;
// import com.example.audit.producer.AuditProducer;

@Tag(name = "Book API", description = "Books CRUD operations")
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private BookRepository repo;
    
    @Autowired
    private AuditProducer auditProducer;

    // @Autowired
    // private AuditProducer auditProducer;

    @PostMapping("/books")
    public Book createBook(
            @RequestBody Book book,
            HttpServletRequest request,
            @RequestHeader(value = "X-USER-ID", required = false) String userId,
            @RequestHeader(value = "X-USERNAME", required = false) String username
    ) {
        Book savedBook = bookService.saveBook(book);

        // TODO: Uncomment the following code once AuditProducer is available
      
        AuditEvent event = AuditEvent.builder()
                .userId(userId)
                .username(username)
                .serviceName("BOOK-SERVICE")
                .operation("CREATE_BOOK")
                .resource("Book")
                .resourceId(savedBook.getId().toString())
                .message("Book created successfully")
                .ipAddress(request.getRemoteAddr())
                .timestamp(LocalDateTime.now())
                .build();

        auditProducer.sendAuditLog(event);
       

        return savedBook;
    }

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
        return bookService.getBookDetails(id);
    }
}
