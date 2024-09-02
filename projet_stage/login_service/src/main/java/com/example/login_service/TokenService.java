package com.example.login_service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenService {

  private static final String SECRET_KEY = "YXNkZmFzZGZhc2RmYXNkZmFzZGZhc2RmYXNkZmFzZGZhc2Rm"; // Replace with your actual secret key

  public String generateToken(Long userId) {
    return Jwts.builder()
      .setSubject(userId.toString())
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1 day expiration
      .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
      .compact();
  }

  public UUID verifyToken(String token) {
    try {
      String userId = Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
      return UUID.fromString(userId);
    } catch (Exception e) {
      return null;
    }
  }
}
