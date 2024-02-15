package com.example.TheFit.login;

import com.example.TheFit.member.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/doLogin")
    public ResponseEntity<TmpResponse> login(LoginRequestDto loginRequestDto){
        Member object = loginService.login(loginRequestDto);

        return new ResponseEntity<>(new TmpResponse(HttpStatus.OK,"login success",object),HttpStatus.OK);
    }
}
