package com.codebyarunyadav.apigateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class GatewayJwtUtil {

    @Value("${jwt.user-secret}")
    private String userSecret;

    @Value("${jwt.internal-secret}")
    private String internalSecret;

    @Value("${jwt.internal-expiration-ms:300000}")
    private long internalExpirationMs;

    private SecretKey userKey() {
        return Keys.hmacShaKeyFor(userSecret.getBytes(StandardCharsets.UTF_8));
    }

    private SecretKey internalKey() {
        return Keys.hmacShaKeyFor(internalSecret.getBytes(StandardCharsets.UTF_8));
    }

    public Claims validateUserToken(String token) {
        return Jwts.parser()
                .verifyWith(userKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateInternalAccessToken(Claims claims) {

        String username = claims.getSubject();
        String role = claims.get("role", String.class);

        Date now = new Date();
        Date expiry = new Date(now.getTime() + internalExpirationMs);

        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .claim("type", "ACCESS")
                .issuedAt(now)
                .expiration(expiry)
                .signWith(internalKey())
                .compact();
    }
}