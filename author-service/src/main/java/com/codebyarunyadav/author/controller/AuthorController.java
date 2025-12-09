package com.codebyarunyadav.author.controller;

import com.codebyarunyadav.author.model.Author;
import com.codebyarunyadav.author.repo.AuthorRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Author API", description = "Authors CRUD operations")
@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository repo;

    @PostMapping
    public Author add(@RequestBody Author a){ return repo.save(a); }

    @GetMapping("/{id}")
    public Author get(@PathVariable Long id){ return repo.findById(id).orElse(null); }

    @GetMapping
    public List<Author> all(){ return repo.findAll(); }
}
