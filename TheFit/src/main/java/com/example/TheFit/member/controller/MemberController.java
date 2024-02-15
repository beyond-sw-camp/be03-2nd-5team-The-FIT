package com.example.TheFit.member.controller;

import com.example.TheFit.member.dto.MemberCreateDto;
import com.example.TheFit.member.dto.MemberReqDto;
import com.example.TheFit.member.dto.MemberResDto;
import com.example.TheFit.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @PostMapping("/member/create")
    public String create(@Valid @RequestBody MemberCreateDto memberCreateDto){
        memberService.create(memberCreateDto);
        return "member create ok";
    }
    @GetMapping("/members")
    public List<MemberResDto> members(){
        return memberService.findAll();
    }
    @PatchMapping("/member/update/{id}")
    public String update(@PathVariable Long id, @RequestBody MemberReqDto memberReqDto) {
        memberService.update(id, memberReqDto);
        return "member update Ok";
    }
    @DeleteMapping("/member/delete/{id}")
    public String delete(@PathVariable Long id) {
        memberService.delete(id);
        return "member delete Ok";
    }


}





