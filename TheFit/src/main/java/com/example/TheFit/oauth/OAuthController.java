package com.example.TheFit.oauth;

import com.example.TheFit.login.TmpResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class OAuthController {
    @Autowired
    private final OAuthService oAuthService;
    private final ObjectMapper objectMapper;

    public OAuthController(OAuthService oAuthService, ObjectMapper objectMapper) {
        this.oAuthService = oAuthService;
        this.objectMapper = objectMapper;
    }

    public GoogleUser getUserInfo(String userInfoResponse) throws JsonProcessingException {
        GoogleUser googleUser = objectMapper.readValue(userInfoResponse, GoogleUser.class);
        return googleUser;
    }

    @GetMapping("/auth/google/callback")
    public ResponseEntity<TmpResponse> successGoogleLogin(@RequestParam("code") String accessCode) throws ParseException, JsonProcessingException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(oAuthService.getGoogleAccessToken(accessCode).getBody());
        System.out.println("HERE:: "+ jsonObject);
        String accessToken = (String) jsonObject.get("access_token");
        String refreshToken = (String) jsonObject.get("refresh_token");
        String idToken =  (String)jsonObject.get("id_token");

        Map<String, Object> oAuthMemberInfo = new HashMap<>();
        oAuthMemberInfo.put("accessToken", accessToken);
        oAuthMemberInfo.put("refreshToken", refreshToken);
        oAuthMemberInfo.put("idToken", idToken);
        GoogleUser googleUser = getUserInfo(oAuthService.decryptBase64UrlToken(idToken.split("\\.")[1]));
        oAuthMemberInfo.put("email", googleUser.getEmail());
        oAuthMemberInfo.put("memberInfo", googleUser);
        return new ResponseEntity<>(new TmpResponse(HttpStatus.OK,
                "login success", oAuthMemberInfo), HttpStatus.OK);
    }


}