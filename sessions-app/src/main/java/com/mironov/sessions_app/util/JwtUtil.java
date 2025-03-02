package com.mironov.sessions_app.util;
import com.mironov.sessions_app.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = System.getenv("JWT_SECRET");

    public String generateToken(String email, Long userId) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId) // Добавляем ID пользователя в полезную нагрузку
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 часов
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }



    // Извлечение ID пользователя из токена
    public Long extractUserId(String token) {
        return (Long) extractAllClaims(token).get("userId");
    }

    // Извлечение имени пользователя
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Извлечение всех параметров
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token, UserDetails user) {
        final String username = extractUsername(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    // "Срок годности токена"
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
