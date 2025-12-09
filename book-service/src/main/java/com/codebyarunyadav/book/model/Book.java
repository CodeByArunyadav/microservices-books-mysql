package com.codebyarunyadav.book.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Long authorId;

    public Book() {}
    public Book(String title, Long authorId){ this.title = title; this.authorId = authorId;}
    public Long getId(){ return id;}
    public void setId(Long id){ this.id = id;}
    public String getTitle(){ return title;}
    public void setTitle(String title){ this.title = title;}
    public Long getAuthorId(){ return authorId;}
    public void setAuthorId(Long authorId){ this.authorId = authorId;}
}
