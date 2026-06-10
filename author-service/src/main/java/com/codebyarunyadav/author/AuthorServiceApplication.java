package com.codebyarunyadav.author;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
	    scanBasePackages = {
	        "com.codebyarunyadav.author",
	        "com.hoxcloud.security"
	    }
	)
public class AuthorServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorServiceApplication.class, args);
    }
}
