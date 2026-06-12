package com.codebyarunyadav.book.config;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
@Slf4j
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {

        return requestTemplate -> {

            ServletRequestAttributes attributes =
                    (ServletRequestAttributes)
                            RequestContextHolder.getRequestAttributes();

            if (attributes == null) {

                log.warn("No request attributes found");
                return;
            }

            HttpServletRequest request =
                    attributes.getRequest();

            String authorizationHeader =
                    request.getHeader("Authorization");

            log.info("Incoming Authorization Header: {}",
                    authorizationHeader);

            if (authorizationHeader != null
                    && !authorizationHeader.isBlank()) {

                requestTemplate.header(
                        "Authorization",
                        authorizationHeader
                );

                log.info("Authorization header forwarded to Feign request");
            } else {

                log.warn("Authorization header missing");
            }
        };
    }
}
