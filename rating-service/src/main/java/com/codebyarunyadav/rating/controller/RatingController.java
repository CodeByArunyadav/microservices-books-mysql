package com.codebyarunyadav.rating.controller;

import com.codebyarunyadav.rating.model.Rating;
import com.example.rating.repo.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingRepository repo;

    @PostMapping
    public Rating add(@RequestBody Rating r){ return repo.save(r); }

    @GetMapping("/book/{bookId}")
    public List<Rating> byBook(@PathVariable Long bookId){ return repo.findByBookId(bookId); }
}
