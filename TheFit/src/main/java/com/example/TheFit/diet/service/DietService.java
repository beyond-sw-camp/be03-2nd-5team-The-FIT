package com.example.TheFit.diet.service;

import com.example.TheFit.diet.domain.Diet;
import com.example.TheFit.diet.dto.DietReqDto;
import com.example.TheFit.diet.dto.DietResDto;
import com.example.TheFit.diet.repository.DietRepository;
import com.example.TheFit.member.domain.Member;
import com.example.TheFit.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class DietService {

    private final DietRepository dietRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public DietService(DietRepository dietRepository, MemberRepository memberRepository) {
        this.dietRepository = dietRepository;
        this.memberRepository = memberRepository;
    }

    public void create(DietReqDto dietReqDto) {
        Member member = memberRepository.findById(dietReqDto.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        Diet diet = Diet.builder()
                .member(member)
                .imagePath(dietReqDto.getImagePath())
                .type(dietReqDto.getType())
                .comment(dietReqDto.getComment())
                .dietDate(dietReqDto.getDietDate())
                .build();
        dietRepository.save(diet);
    }

    public DietResDto findById(Long id) {
        Diet diet = dietRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Diet not found"));
        return DietResDto.builder()
                .id(diet.getId())
                .memberId(diet.getMember().getId())
                .imagePath(diet.getImagePath())
                .type(diet.getType())
                .comment(diet.getComment())
                .dietDate(diet.getDietDate())
                .build();
    }

    public void update(Long id, DietReqDto dietReqDto) {
        Diet diet = dietRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Diet not found"));
        diet.update(dietReqDto);
    }

    public void delete(Long id) {
        dietRepository.deleteById(id);
    }
}