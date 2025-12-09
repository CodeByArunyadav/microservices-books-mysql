package com.codebyarunyadav.book.DTO;

import java.util.List;

import com.codebyarunyadav.book.model.Book;

import lombok.Data;

@Data
public class BookResponse {
	    //private Long id;
	    //private String title;
	    //private String description;
	    private Book book;

	    private AuthorDTO author;
	    private List<RatingDTO> ratings;

}