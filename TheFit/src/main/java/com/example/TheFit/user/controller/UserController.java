package com.example.TheFit.user.controller;

import com.example.TheFit.common.TheFitResponse;
import com.example.TheFit.security.TokenService;
import com.example.TheFit.user.service.UserService;
import com.example.TheFit.user.dto.UserIdPassword;
import com.example.TheFit.user.dto.UserLoginRequestDto;
import com.example.TheFit.user.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private final TokenService tokenService;
    private final UserService userService;

    public UserController(MemberService memberService, TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping("/dologin")
    public ResponseEntity<TheFitResponse> doLogin(@RequestBody UserLoginRequestDto userLoginRequestDto){
        UserIdPassword userIdPassword = userService.login(userLoginRequestDto);
        String accessToken = tokenService.createAccessToken(userIdPassword.getEmail(),userIdPassword.getRole().toString());
        String refreshToken = tokenService.createRefreshToken(userIdPassword.getEmail());
        Map<String,Object> memberInfo = new HashMap<>();
        memberInfo.put("token",accessToken);
        memberInfo.put("refreshToken",refreshToken);
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.OK,"success login",memberInfo),HttpStatus.OK);
    }
}
