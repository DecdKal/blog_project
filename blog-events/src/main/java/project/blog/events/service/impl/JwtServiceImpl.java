package project.blog.events.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;
import project.blog.events.service.JwtService;

@Service
public class JwtServiceImpl implements JwtService {

    private final String jwtSecret;

    public JwtServiceImpl(
            @Value("${jwt.secret}") String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }
    @Override
    public UserDetails extractUserInformation(String jwtToken) {
        Claims claims = extractClaims(jwtToken);

        String username = claims.getSubject();
        List<String> roles = getRoles(claims);

        return new User(username, "", roles.stream().map(SimpleGrantedAuthority::new).toList());
    }

    @SuppressWarnings("unchecked")
    private static List<String> getRoles(Claims claims) {
        return claims.get("roles", List.class);
    }

    private Claims extractClaims(String jwtToken) {
        return Jwts
                .parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
