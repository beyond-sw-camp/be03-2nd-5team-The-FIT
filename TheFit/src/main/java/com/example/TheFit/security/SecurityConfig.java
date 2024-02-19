package com.example.TheFit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    private final JwtAuthFilter jwtAuthFilter;
    @Autowired
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    public SecurityConfig(JwtAuthFilter jwtAuthFilter, ExceptionHandlerFilter exceptionHandlerFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.exceptionHandlerFilter = exceptionHandlerFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .cors().and()
                .httpBasic().disable()
                .authorizeRequests()
                    .antMatchers("/member/create","/member/doLogin","/trainer/create","/reCreateAccessToken")
                    .permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(exceptionHandlerFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
