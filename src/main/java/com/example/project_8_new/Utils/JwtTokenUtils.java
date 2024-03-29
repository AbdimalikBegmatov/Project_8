package com.example.project_8_new.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.*;

@Component
public class JwtTokenUtils {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.lifetime}")
    private Duration lifetime;

    public String generateToken(String email){
        Map<String,Object> claim = new HashMap<>();

        claim.put("email",email);

        Date issuedDate = new Date();
        Date expirationDate = new Date(issuedDate.getTime()+lifetime.toMillis());


        return Jwts.builder()
                .claims(claim)
                .subject(email)
                .issuedAt(issuedDate)
                .expiration(expirationDate)
                .signWith(secretKey())
                .compact();

    }
    public Boolean isTokenValid(String token, UserDetails userDetails){
        String username = getAllClaimsFromToken(token).getSubject();
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    public String getUserEmail(String token){
        return getAllClaimsFromToken(token).getSubject();
    }
    public boolean isTokenExpired(String token){
        return getAllClaimsFromToken(token).getExpiration().before(new Date());
    }
    public Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey secretKey(){
        byte[] keyBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
