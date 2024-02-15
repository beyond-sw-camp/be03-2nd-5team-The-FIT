package com.example.TheFit.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = ((HttpServletRequest) request).getHeader("Authorization");
        String refreshToken = ((HttpServletRequest) request).getHeader("refreshToken");
        if(bearerToken != null){
            if(!bearerToken.substring(0,7).equals("Bearer ")){
                throw new AuthenticationServiceException("token의 형식이 맞지않습니다.");
            }
            String token = bearerToken.substring(7);
            Claims claims = null;
            try {
                claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_"+claims.get("role")));
                UserDetails userDetails = new User(claims.getSubject(),"",authorities);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch (ExpiredJwtException e){
//                RefreshToken refreshTokenValue = refreshTokenRepository.findById(decode(token)).orElseThrow(()->new JwtException("refreshToken invalid"));
//                if(!refreshToken.equals(refreshTokenValue.getRefreshToken())){
//                    // 모든 접근 거부
//                    throw new AuthenticationServiceException("refresh token의 형식이 맞지않습니다.");
//                }
//                token = jwtTokenProvider.createToken(refreshTokenValue.getEmail(),secretKey);
            }
        }
        filterChain.doFilter(request,response);
    }
}
