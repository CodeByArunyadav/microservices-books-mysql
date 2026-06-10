package com.codebyarunyadav.rating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
	    scanBasePackages = {
	        "com.codebyarunyadav.rating",
	        "com.hoxcloud.security"
	    }
	)
public class RatingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RatingServiceApplication.class, args);
    }
}
