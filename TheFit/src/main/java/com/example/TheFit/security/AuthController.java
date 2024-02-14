package com.example.TheFit.security;

import com.example.TheFit.login.TmpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    // TODO : 권한이 필요하지 않음
    @GetMapping("/reCreateAccessToken")
    public ResponseEntity<TmpResponse> reCreateAccessToken(){

        return null;
    }
}
