package com.example.TheFit.member.controller;


import com.example.TheFit.login.TmpResponse;
import com.example.TheFit.member.domain.Member;
import com.example.TheFit.member.dto.MemberCreateDto;
import com.example.TheFit.member.dto.MemberLoginDto;
import com.example.TheFit.member.dto.MemberReqDto;
import com.example.TheFit.member.dto.MemberResDto;
import com.example.TheFit.member.service.MemberService;
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
    public String create(@Valid @RequestBody MemberReqDto memberReqDto){
        memberService.create(memberReqDto);
        return "member create ok";
    }
    @GetMapping("/list")
    public List<MemberResDto> members(){
        return memberService.findAll();
    }
    @PatchMapping("/update/{id}")
    public String update(@PathVariable Long id, @Valid @RequestBody MemberReqDto memberReqDto) {
        memberService.update(id, memberReqDto);
        return "member update Ok";
    }

    @DeleteMapping("/member/delete/{id}")
    public String delete(@Valid @PathVariable Long id) {
        memberService.delete(id);
        return "member delete Ok";
    }

    @GetMapping("member/doLogin")
    public ResponseEntity<TmpResponse> memberLogin(@RequestBody MemberLoginDto memberLoginDto){
        Member member = memberService.login(memberLoginDto);
        String accessToken = tokenService.createAccessToken(member.getEmail(),member.getName());
        String refreshToken = tokenService.createRefreshToken(member.getEmail());
        Map<String,Object> memberInfo = new HashMap<>();
        memberInfo.put("token",accessToken);
        memberInfo.put("refreshToken",refreshToken);
        return new ResponseEntity<>(new TmpResponse(HttpStatus.OK,"login success",memberInfo),HttpStatus.OK);
    }


}

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        memberService.delete(id);
        return "member delete Ok";
    }
}
