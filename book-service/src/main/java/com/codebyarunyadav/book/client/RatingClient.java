package com.codebyarunyadav.book.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.codebyarunyadav.book.DTO.RatingDTO;
import com.codebyarunyadav.book.config.FeignConfig;

import java.util.List;

@FeignClient(name = "RATING-SERVICE",configuration = FeignConfig.class)
public interface RatingClient {
    @GetMapping("/ratings/book/{bookId}")
    List<RatingDTO> getRatings(@PathVariable Long bookId);
}
