package com.example.TheFit.member.controller;

import com.example.TheFit.member.dto.MemberReqDto;
import com.example.TheFit.member.dto.MemberResDto;
import com.example.TheFit.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
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
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        memberService.delete(id);
        return "member delete Ok";
    }
}