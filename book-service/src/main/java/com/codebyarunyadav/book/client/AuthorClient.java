package com.codebyarunyadav.book.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.codebyarunyadav.book.DTO.AuthorDTO;
import com.codebyarunyadav.book.config.FeignConfig;


@FeignClient(name = "AUTHOR-SERVICE",configuration = FeignConfig.class)
public interface AuthorClient {
    @GetMapping("/authors/{id}")
    AuthorDTO getAuthor(@PathVariable Long id);
}