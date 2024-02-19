package com.example.TheFit.member.service;

import com.example.TheFit.deitfeedback.mapper.DietFeedBackMapper;
import com.example.TheFit.member.domain.Member;
import com.example.TheFit.member.dto.MemberReqDto;
import com.example.TheFit.member.dto.MemberResDto;
import com.example.TheFit.member.mapper.MemberMapper;
import com.example.TheFit.member.repository.MemberRepository;
import com.example.TheFit.trainer.domain.Trainer;
import com.example.TheFit.trainer.repository.TrainerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper = MemberMapper.INSTANCE;
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
        Member member = MemberMapper.INSTANCE.toEntity(memberReqDto);
        member.setTrainer(trainer);
        memberRepository.save(member);
    }

    public List<MemberResDto> findAll() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(memberMapper::toDto)
                .collect(Collectors.toList());
    }
    public void update(Long id, MemberReqDto memberReqDto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        Trainer trainer = trainerRepository.findById(member.getTrainer().getId())
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found"));
        MemberMapper.INSTANCE.update(memberReqDto, member);
        member.setTrainer(trainer);
        memberRepository.save(member);
    }

    public void delete(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found member"));
        member.delete();
    }
}