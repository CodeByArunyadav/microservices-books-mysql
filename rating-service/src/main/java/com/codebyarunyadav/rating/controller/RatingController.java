package com.codebyarunyadav.rating.controller;

import com.codebyarunyadav.rating.model.Rating;
import com.codebyarunyadav.rating.repo.RatingRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Rating API", description = "Rating CRUD operations")
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
