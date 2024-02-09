package com.example.TheFit.member.controller;

import com.example.TheFit.member.dto.MemberCreateDto;
import com.example.TheFit.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @PostMapping("/member/createa")
    public String create(@RequestBody MemberCreateDto memberCreateDto){
        memberService.create(memberCreateDto);
        return "member create ok";
    }
}





