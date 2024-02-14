package com.example.TheFit.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TokenService {
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Integer expiration;

    public String createAccessToken(Object o){

        return null;
    }

    public String createRefreshToken(Object o){

        return null;
    }

    public Map<Object,Object> decodeAccessTokenPayload(){

        return null;
    }
}
