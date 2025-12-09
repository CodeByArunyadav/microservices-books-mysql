package com.codebyarunyadav.book.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.codebyarunyadav.book.DTO.AuthorDTO;


@FeignClient(name = "AUTHOR-SERVICE")
public interface AuthorClient {
    @GetMapping("/authors/{id}")
    AuthorDTO getAuthor(@PathVariable Long id);
}