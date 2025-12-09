package com.example.rating.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codebyarunyadav.rating.model.Rating;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByBookId(Long bookId);
}
