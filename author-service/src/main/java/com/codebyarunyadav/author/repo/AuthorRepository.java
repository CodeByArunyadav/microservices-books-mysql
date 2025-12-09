package com.codebyarunyadav.author.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codebyarunyadav.author.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> { }
