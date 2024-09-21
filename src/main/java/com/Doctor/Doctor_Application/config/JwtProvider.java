package com.Doctor.Doctor_Application.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.io.IOException;


@Component
public class JwtProvider {

     static SecretKey jwtSecret = Keys.hmacShaKeyFor("asdfghjklmnbghyujnbvcxsertyubcxbvf".getBytes());

    // Generate a JWT token for the authenticated user
    public static String generateToken(Authentication authentication) {
        // Token expiration time in milliseconds
        int jwtExpirationInMs = 864000000;
        return Jwts.builder()
                .claim("email",authentication.getName())
                .setIssuedAt(new Date()) // Set the token creation time
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs)) // Set the expiration time
                .signWith(jwtSecret)
                .compact();
    }

    // Retrieve the email from the JWT token
    public static  String getEmailFromToken(String token) {
        Claims claims= Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();
        return String.valueOf(claims.get("email"));
    }

    // Validate the token (check expiration and integrity)
    public  static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();
            return true;
        } catch (Exception e) {
            // Log the error message here if needed
            return false;
        }
    }





}
