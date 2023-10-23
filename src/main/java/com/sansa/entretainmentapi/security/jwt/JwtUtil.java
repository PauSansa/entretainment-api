package com.sansa.entretainmentapi.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Slf4j
@Component
@NoArgsConstructor
public class JwtUtil {
    // create random set of bit for secret
    @Value("${entertainmentapi.jwt.secret}")
    private String secretKey;

    @Value("${entertainmentapi.jwt.expiration}")
    private int expirationTime;

    public String encode(String subject){

        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + (long) this.expirationTime * 1000))
                .signWith(getSigninKey())
                .compact();
    }

    public JwtParser getParser(){
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build();
    }

    public Claims getAllClaims(String jws){
        return this.getParser().parseClaimsJws(jws).getBody();
    }

    public String getSubject(String jws){
        return this.getAllClaims(jws).getSubject();
    }

    private Key getSigninKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean verify(String jws){
        return this.getAllClaims(jws).getExpiration().after(new Date());
    }
}
