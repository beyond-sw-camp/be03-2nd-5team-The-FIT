package com.example.TheFit.member.service;

import com.example.TheFit.member.domain.Gender;
import com.example.TheFit.member.domain.Member;
import com.example.TheFit.member.dto.MemberCreateDto;
import com.example.TheFit.member.dto.MemberReqDto;
import com.example.TheFit.member.dto.MemberResDto;
import com.example.TheFit.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
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

    public List<MemberResDto> findAll() {
        List<Member> members = memberRepository.findAll();
        List<MemberResDto> memberResDtos = new ArrayList<>();
        for (Member member : members) {
            MemberResDto memberResDto = MemberResDto.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .email(member.getEmail())
                    .cmHeight(member.getCmHeight())
                    .kgWeight(member.getKgWeight())
                    .gender(member.getGender())
                    .profileImage(member.getProfileImage())
                    .phoneNumber(member.getPhoneNumber())
                    .build();
            memberResDtos.add(memberResDto);
        }
        return memberResDtos;
    }

    public Member update(Long id, MemberReqDto memberReqDto) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("not found member"));
        member.update(
                memberReqDto.getName(),
                memberReqDto.getPassword(),
                memberReqDto.getCmHeight(),
                memberReqDto.getKgWeight(),
                memberReqDto.getProfileImage(),
                memberReqDto.getPhoneNumber());
        return memberRepository.save(member);
    }
    @Transactional
    public void delete(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found member"));
        member.delete();
        memberRepository.save(member);
    }
}