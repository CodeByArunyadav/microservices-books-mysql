package com.hoxcloud.security.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hoxcloud.security.constants.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtilValidator {

    @Value("${jwt.secret}")
    private String secretKey;

    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(
                secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // Extract Username
    public String extractUsername(String token) {

        return extractAllClaims(token).getSubject();
    }

    // Extract Role
    public String extractRole(String token) {

        return extractAllClaims(token)
                .get(SecurityConstants.ROLE_CLAIM, String.class);
    }

    // Extract Token Type
    public String extractTokenType(String token) {

        return extractAllClaims(token)
                .get(SecurityConstants.TOKEN_TYPE_CLAIM, String.class);
    }

    // Validate Access Token
    public boolean validateToken(String token, String username) {

        try {

            String extractedUsername =
                    extractUsername(token);

            String tokenType =
                    extractTokenType(token);

            return extractedUsername.equals(username)
                    && SecurityConstants.ACCESS_TOKEN
                            .equals(tokenType)
                    && !isTokenExpired(token);

        } catch (Exception e) {

            return false;
        }
    }

    // Check Expiry
    private boolean isTokenExpired(String token) {

        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    // Extract Claims
    public Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}