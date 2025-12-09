package com.codebyarunyadav.book.DTO;

public class RatingDTO {

    private Long id;
    private Long bookId;
    private int stars;
    private String review;

    public RatingDTO() {}

    public RatingDTO(Long id, Long bookId, int stars, String review) {
        this.id = id;
        this.bookId = bookId;
        this.stars = stars;
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}