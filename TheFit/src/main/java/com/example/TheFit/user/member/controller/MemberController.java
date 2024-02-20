package com.example.TheFit.user.member.controller;


import com.example.TheFit.common.TheFitResponse;
import com.example.TheFit.user.member.domain.Member;
import com.example.TheFit.user.member.dto.MemberReqDto;
import com.example.TheFit.user.member.dto.MemberResDto;
import com.example.TheFit.user.member.service.MemberService;
import com.example.TheFit.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/myInfo")
    public ResponseEntity<TheFitResponse> myInfo(){
        MemberResDto memberResDto = memberService.findMyInfo();
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.CREATED,"success check",memberResDto),HttpStatus.CREATED);
    }
    @DeleteMapping("/member/delete/{id}")
    public ResponseEntity<TheFitResponse> delete(@Valid @PathVariable Long id) {
        memberService.delete(id);
        return new ResponseEntity<>(new TheFitResponse(HttpStatus.CREATED,"success delete",null),HttpStatus.CREATED);
    }
}


