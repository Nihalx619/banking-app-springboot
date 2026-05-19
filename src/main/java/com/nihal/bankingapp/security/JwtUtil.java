package com.nihal.bankingapp.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private String secretKey = "your-secret-key-which-should-be-very-long-and-secure";
	
	public String generateToken(String username) {
	    byte[] keyBytes = secretKey.getBytes();
	    SecretKey key = Keys.hmacShaKeyFor(keyBytes);
	    
	    return Jwts.builder()
	        .setSubject(username)
	        .setIssuedAt(new Date())
	        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
	        .signWith(key)
	        .compact();
	}
	
	public String extractUsername(String token) {
	    byte[] keyBytes = secretKey.getBytes();
	    SecretKey key = Keys.hmacShaKeyFor(keyBytes);
	    
	    return Jwts.parserBuilder()
	        .setSigningKey(key)
	        .build()
	        .parseClaimsJws(token)
	        .getBody()
	        .getSubject();
	}
	
	public boolean validateToken(String token, String username) {
          String extractedUser = extractUsername(token);
          if(extractedUser.equals(username)) { 
        	 return true;
          }
          return false;
	}
}
