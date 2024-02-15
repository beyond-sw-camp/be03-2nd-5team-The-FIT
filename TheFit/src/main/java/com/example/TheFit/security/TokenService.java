package com.example.TheFit.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
public class TokenService {
    @Value("${jwt.secretKey}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private Integer expiration;
    @Autowired
    private final RedisRepository redisRepository;
    public TokenService(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    public String createAccessToken(String email,String role){
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role",role);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+expiration*60*1000L))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }

    public String createRefreshToken(String email){
        String refreshTokenValue = UUID.randomUUID().toString();
        RefreshToken refreshToken = new RefreshToken(email,refreshTokenValue);
        redisRepository.save(refreshToken);
        return refreshTokenValue;
    }

    public Map<Object,Object> decodeAccessTokenPayload(){

        return null;
    }
}
