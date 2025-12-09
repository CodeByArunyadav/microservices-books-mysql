package com.codebyarunyadav.book.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codebyarunyadav.book.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> { }
