package com.example.TheFit.member.service;

import com.example.TheFit.member.domain.Gender;
import com.example.TheFit.member.domain.Member;
import com.example.TheFit.member.dto.MemberCreateDto;
import com.example.TheFit.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public void create(MemberCreateDto memberCreateDto) {
        Member member = Member.builder()
                .name(memberCreateDto.getName())
                .email(memberCreateDto.getEmail())
                .password(memberCreateDto.getPassword())
                .cmHeight(memberCreateDto.getCmHeight())
                .kgWeight(memberCreateDto.getKgWeight())
                .gender(Gender.MALE)
                .phoneNumber(memberCreateDto.getPhoneNumber())
                .build();
        memberRepository.save(member);
    }
}