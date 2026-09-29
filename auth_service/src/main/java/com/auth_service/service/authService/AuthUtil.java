package com.auth_service.service.authService;

import com.auth_service.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Service
public class AuthUtil {

    @Value("${jwt.secretKey}")
    private String secretKey;

    public SecretKey getSecretKey(User user) {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user) {

        List<String> roles = user.getRoles()
                .stream()
                .map(role -> "ROLE_" + role.getRoleType().name().toUpperCase())
                .toList();

        roles.forEach(System.out::println);

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("userId", user.getId())
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSecretKey(user))
                .compact();
    }
}
