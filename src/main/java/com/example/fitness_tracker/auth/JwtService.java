package com.example.fitness_tracker.auth;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.List;

@Component
public class JwtService {
    private static final Logger log = LoggerFactory.getLogger(JwtService.class);
    private final String secret;
    private final CustomUserDetailsService userDetailsService;

    public JwtService(@Value("${jwt.secret}") String secret, CustomUserDetailsService userDetailsService) {
        this.secret = secret;
        this.userDetailsService = userDetailsService;
    }

    public String createToken(String username) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        JsonObject content = new JsonObject();
        content.add("username", new JsonPrimitive(username));

        return Jwts.builder().subject(content.toString()).signWith(key).compact();
    }

    public String resolveToken(String authHeader) {
        return authHeader.substring(7);
    }

    public Boolean validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        String username = getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, List.of());
    }


    public String getUsername(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        String subject = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
        JsonElement result = JsonParser.parseString(subject);
        JsonObject obj = result.getAsJsonObject();
        return obj.get("username").getAsString();
    }
}
