package com.example.TheFit.oauth;

import com.example.TheFit.login.TmpResponse;
import com.example.TheFit.security.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OAuthController {
    @Autowired
    private final OAuthService oAuthService;
    private final ObjectMapper objectMapper;
    private final TokenService tokenService;

    public OAuthController(OAuthService oAuthService, ObjectMapper objectMapper,
                           TokenService tokenService) {
        this.oAuthService = oAuthService;
        this.objectMapper = objectMapper;
        this.tokenService = tokenService;
    }

    public GoogleUser getUserInfo(String userInfoResponse) throws JsonProcessingException {
        GoogleUser googleUser = objectMapper.readValue(userInfoResponse, GoogleUser.class);
        return googleUser;
    }

    @GetMapping("/auth/google/callback")
    public RedirectView successGoogleLogin(@RequestParam("code") String accessCode) throws ParseException, JsonProcessingException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(oAuthService.getGoogleAccessToken(accessCode).getBody());
        String idToken = (String) jsonObject.get("id_token");
        Map<String, Object> oAuthMemberInfo = new HashMap<>();
        GoogleUser googleUser = getUserInfo(oAuthService.decryptBase64UrlToken(idToken.split("\\.")[1]));
        oAuthMemberInfo.put("email", googleUser.getEmail());
        oAuthMemberInfo.put("name", googleUser.getName());
        String role = oAuthService.findRole(googleUser.getEmail());
        String accessToken = tokenService.createAccessToken(googleUser.email, googleUser.getName(), role);
        String refreshToken = tokenService.createRefreshToken(googleUser.email);
        String redirectUrl = "http://localhost:8081/loginSuccess/?accessToken=" + accessToken
                + "&refreshToken=" + refreshToken
                + "&role=" + role;
        return new RedirectView(redirectUrl);
    }
}
