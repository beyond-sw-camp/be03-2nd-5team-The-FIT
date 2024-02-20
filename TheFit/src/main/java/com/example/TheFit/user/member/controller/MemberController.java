package com.example.TheFit.user.member.controller;


import com.example.TheFit.common.TheFitResponse;
import com.example.TheFit.login.TmpResponse;
import com.example.TheFit.user.member.domain.Member;
import com.example.TheFit.user.member.dto.MemberLoginDto;
import com.example.TheFit.user.member.dto.MemberReqDto;
import com.example.TheFit.user.member.dto.MemberResDto;
import com.example.TheFit.user.member.service.MemberService;
import com.example.TheFit.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final TokenService tokenService;
    private final MemberService memberService;
    @Autowired
    public MemberController(TokenService tokenService, MemberService memberService) {
        this.tokenService = tokenService;
        this.memberService = memberService;
    }
    @PostMapping("/create")
    public ResponseEntity<TheFitResponse> create(@Valid @RequestBody MemberReqDto memberReqDto){
        Member member = memberService.create(memberReqDto);
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.CREATED,"success create",member.getId()),HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public ResponseEntity<TheFitResponse> members(){
        List<MemberResDto> memberResDtos =  memberService.findAll();
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.CREATED,"success check",memberResDtos),HttpStatus.CREATED);
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<TheFitResponse> update(@PathVariable Long id, @Valid @RequestBody MemberReqDto memberReqDto) {
        Member member = memberService.update(id, memberReqDto);
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.CREATED,"success update",member.getId()),HttpStatus.CREATED);
    }

    @DeleteMapping("/member/delete/{id}")
    public ResponseEntity<TheFitResponse> delete(@Valid @PathVariable Long id) {
        memberService.delete(id);
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.CREATED,"success delete",null),HttpStatus.CREATED);
    }

    @GetMapping("/doLogin")
    public ResponseEntity<TheFitResponse> memberLogin(@RequestBody MemberLoginDto memberLoginDto){
        Member member = memberService.login(memberLoginDto);
        String accessToken = tokenService.createAccessToken(member.getEmail(),member.getName());
        String refreshToken = tokenService.createRefreshToken(member.getEmail());
        Map<String,Object> memberInfo = new HashMap<>();
        memberInfo.put("token",accessToken);
        memberInfo.put("refreshToken",refreshToken);
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.OK,"success login",memberInfo),HttpStatus.OK);
    }
}


