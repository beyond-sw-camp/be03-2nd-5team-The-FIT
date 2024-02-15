package com.example.TheFit.login;

import com.example.TheFit.member.domain.Member;
import com.example.TheFit.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private final LoginService loginService;
    @Autowired
    private final TokenService tokenService;
    public LoginController(LoginService loginService, TokenService tokenService) {
        this.loginService = loginService;
        this.tokenService = tokenService;
    }

    @GetMapping("/doLogin")
    public ResponseEntity<TmpResponse> login(LoginRequestDto loginRequestDto){
        Member member = loginService.login(loginRequestDto);
        //현재 트레이너와 트레이니의 부모 클래스가 아직 미구현임으로 loginRequestDto에서 임시로 role를 넘겨서 코드진행
        String accessToken = tokenService.createAccessToken(member.getEmail(),loginRequestDto.getRole());
        String refreshToken = tokenService.createRefreshToken(member.getEmail());
        Map<String,Object> memberInfo = new HashMap<>();
        memberInfo.put("token",accessToken);
        memberInfo.put("refreshToken",refreshToken);
        return new ResponseEntity<>(new TmpResponse(HttpStatus.OK,"login success",memberInfo),HttpStatus.OK);
    }
}
