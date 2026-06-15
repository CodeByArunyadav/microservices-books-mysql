package com.hoxcloud.security.filter;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hoxcloud.security.constants.SecurityConstants; 
import com.hoxcloud.security.util.JwtUtilValidator;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final Logger logger =
            LoggerFactory.getLogger(JwtAuthFilter.class);

    private final JwtUtilValidator jwtUtilValidator;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);

        String token = null;
        String username = null;

        if (authHeader != null &&
            authHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {

            token = authHeader.substring(7);

            try {

                username = jwtUtilValidator.extractUsername(token);

            } catch (Exception e) {

                logger.warn("Invalid JWT Token");
            }
        }

        if (username != null &&
            SecurityContextHolder.getContext()
                                 .getAuthentication() == null) {

            if (jwtUtilValidator.validateToken(token, username)) {

                String role = jwtUtilValidator.extractRole(token);

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                List.of(new SimpleGrantedAuthority(
                                        SecurityConstants.ROLE_PREFIX + role)));

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request));

                SecurityContextHolder.getContext()
                                     .setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}