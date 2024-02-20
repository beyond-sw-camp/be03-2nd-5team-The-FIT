package com.example.TheFit.user.member.service;

import com.example.TheFit.user.UserMapper;
import com.example.TheFit.user.member.domain.Member;
import com.example.TheFit.user.member.dto.MemberLoginDto;
import com.example.TheFit.user.member.dto.MemberReqDto;
import com.example.TheFit.user.member.dto.MemberResDto;
import com.example.TheFit.user.member.repository.MemberRepository;
import com.example.TheFit.user.trainer.domain.Trainer;
import com.example.TheFit.user.trainer.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberService {
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final TrainerRepository trainerRepository;

    public MemberService(MemberRepository memberRepository, UserMapper userMapper, TrainerRepository trainerRepository) {
        this.memberRepository = memberRepository;
        this.userMapper = userMapper;
        this.trainerRepository = trainerRepository;
    }

    public Member create(MemberReqDto memberReqDto) {
        Trainer trainer = trainerRepository.findById(memberReqDto.getTrainerId())
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found"));
        Member member = userMapper.toEntity(memberReqDto,trainer);
        Member member1 = memberRepository.findById(1L).orElseThrow();
        System.out.println(member1.getTrainer().name);
        return  memberRepository.save(member);
    }

    public List<MemberResDto> findAll() {
        trainerRepository.findAll();
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
    public Member update(Long id, MemberReqDto memberReqDto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        Trainer trainer = trainerRepository.findById(member.getTrainer().getId())
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found"));
        member.update(memberReqDto,trainer);
        return member;
    }

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

    public MemberResDto findMyInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByEmail(authentication.getName()).orElseThrow();
        return userMapper.toDto(member);
    }
}