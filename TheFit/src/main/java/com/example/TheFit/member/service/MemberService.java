package com.example.TheFit.member.service;

import com.example.TheFit.member.domain.Member;
import com.example.TheFit.member.dto.MemberReqDto;
import com.example.TheFit.member.dto.MemberResDto;
import com.example.TheFit.member.repository.MemberRepository;
import com.example.TheFit.trainer.domain.Trainer;
import com.example.TheFit.trainer.repository.TrainerRepository;
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
    private final TrainerRepository trainerRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, TrainerRepository trainerRepository) {
        this.memberRepository = memberRepository;
        this.trainerRepository = trainerRepository;
    }

    public void create(MemberReqDto memberReqDto) {
        Trainer trainer = trainerRepository.findById(memberReqDto.getTrainerId())
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found"));
        Member member = Member.builder()
                .trainer(trainer)
                .name(memberReqDto.getName())
                .email(memberReqDto.getEmail())
                .password(memberReqDto.getPassword())
                .cmHeight(memberReqDto.getCmHeight())
                .kgWeight(memberReqDto.getKgWeight())
                .gender(memberReqDto.getGender())
                .phoneNumber(memberReqDto.getPhoneNumber())
                .build();
        memberRepository.save(member);
    }

    public List<MemberResDto> findAll() {
        List<Member> members = memberRepository.findAll();
        List<MemberResDto> memberResDtos = new ArrayList<>();
        for (Member member : members) {
            MemberResDto memberResDto = MemberResDto.builder()
                    .id(member.getId())
                    .trainerId(member.getTrainer() != null ? member.getTrainer().getId() : null)
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
}