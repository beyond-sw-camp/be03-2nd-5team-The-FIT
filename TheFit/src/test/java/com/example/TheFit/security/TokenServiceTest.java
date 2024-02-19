package com.example.TheFit.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.crypto.Data;

import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TokenServiceTest {
    @Autowired
    private TokenService tokenService;

    @Test
    void decodeAccessTokenPayload() throws JsonProcessingException {
        Claims claims = Jwts.claims();
        claims.setSubject("test");
        Date now = new Date();
        String secretKey = "testTokenDecode";
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+30*60*1000L))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
        Map<String,Object> map = tokenService.decodeAccessTokenPayload(token);
        System.out.println(map);
        Assertions.assertEquals("test",map.get("sub"));
    }
}