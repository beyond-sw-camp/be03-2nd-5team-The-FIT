package com.example.TheFit.user.member.service;

import com.example.TheFit.user.UserMapper;
import com.example.TheFit.user.member.domain.Member;
import com.example.TheFit.user.member.dto.MemberLoginDto;
import com.example.TheFit.user.member.dto.MemberReqDto;
import com.example.TheFit.user.member.dto.MemberResDto;
import com.example.TheFit.user.member.repository.MemberRepository;
import com.example.TheFit.user.trainer.domain.Trainer;
import com.example.TheFit.user.trainer.repository.TrainerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final TrainerRepository trainerRepository;
    Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    public MemberService(MemberRepository memberRepository, TrainerRepository trainerRepository) {
        this.memberRepository = memberRepository;
        this.trainerRepository = trainerRepository;
    }

    public void create(MemberReqDto memberReqDto) {
        Trainer trainer = trainerRepository.findById(memberReqDto.getTrainerId())
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found"));
        Member member = userMapper.INSTANCE.toEntity(memberReqDto);
        member.setTrainer(trainer);
        memberRepository.save(member);
    }

    public List<MemberResDto> findAll() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
    public void update(Long id, MemberReqDto memberReqDto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        Trainer trainer = trainerRepository.findById(member.getTrainer().getId())
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found"));
        userMapper.INSTANCE.update(memberReqDto, member);
        member.setTrainer(trainer);
        memberRepository.save(member);
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
}