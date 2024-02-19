package com.example.TheFit.member.service;

import com.example.TheFit.member.domain.Gender;
import com.example.TheFit.member.domain.Member;
import com.example.TheFit.member.dto.MemberCreateDto;
import com.example.TheFit.member.dto.MemberLoginDto;
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
            Long trainerId = member.getTrainer() != null ? member.getTrainer().getId() : null;
            MemberResDto memberResDto = MemberResDto.builder()
                    .id(member.getId())
                    .TrainerId(trainerId)
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
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found"));
        member.update(memberReqDto);
        return memberRepository.save(member);
    }

    @Transactional
    public void delete(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found member"));
        member.delete();
    }

    public Member login(MemberLoginDto memberLoginDto) {
        Member member = memberRepository.findByEmail(memberLoginDto.getEmail()).orElseThrow(()-> new EntityNotFoundException("해당 이메일이 없습니다"));
        if(!member.getPassword().equals(memberLoginDto.getPassword())){
            throw new IllegalArgumentException("비밀번호가 틀립니다");
        }
        return member;
    }
}