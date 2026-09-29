package com.user_service.service.jwt_service;

import com.user_service.exception.ClaimNotFoundException;
import com.user_service.exception.EmailNotFound;
import com.user_service.exception.UserIdNotFound;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String secretKey;

    private final Logger log = LoggerFactory.getLogger(JwtService.class);

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSignKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            log.error("Invalid JWT token", e);
            return false;
        }
    }

    public String extractEmail(String token) {
        Claims claims = getClaims(token);
        if (claims == null) throw new ClaimNotFoundException("JWT claims not found");
        String email = claims.getSubject();
        if (email == null) email = claims.get("email", String.class);
        if (email == null) throw new EmailNotFound("JWT token does not contain email claim");
        return email;
    }

    public List<GrantedAuthority> extractRoles(String token) {
        Claims claims = getClaims(token);
        if (claims == null) throw new ClaimNotFoundException("JWT claims not found");
        List<String> roles = claims.get("roles", List.class);
        if (roles == null) return List.of();
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public Long extractUserId(String token) {
        Claims claims = getClaims(token);
        if (claims == null) throw new ClaimNotFoundException("JWT claims not found");
        Long userId = claims.get("userId", Long.class);
        if (userId == null) throw new UserIdNotFound("JWT token does not contain userId claim");
        return userId;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSignKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Failed to parse JWT claims", e);
            return null;
        }
    }
}