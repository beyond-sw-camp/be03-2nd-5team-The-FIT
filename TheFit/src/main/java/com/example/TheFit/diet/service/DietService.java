package com.example.TheFit.diet.service;

import com.example.TheFit.common.ErrorCode;
import com.example.TheFit.common.TheFitBizException;
import com.example.TheFit.diet.domain.Diet;
import com.example.TheFit.diet.dto.DietReqDto;
import com.example.TheFit.diet.dto.DietResDto;
import com.example.TheFit.diet.mapper.DietMapper;
import com.example.TheFit.diet.repository.DietRepository;
import com.example.TheFit.user.member.domain.Member;
import com.example.TheFit.user.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DietService {

    private final DietRepository dietRepository;
    private final MemberRepository memberRepository;
    private final DietMapper dietMapper;

    @Autowired
    public DietService(DietRepository dietRepository, MemberRepository memberRepository, DietMapper dietMapper) {
        this.dietRepository = dietRepository;
        this.memberRepository = memberRepository;
        this.dietMapper = dietMapper;
    }

    public Diet create(DietReqDto dietReqDto)throws TheFitBizException {
        Member member = memberRepository.findById(dietReqDto.getMemberId())
                .orElseThrow(() -> new TheFitBizException(ErrorCode.NOT_FOUND_MEMBER));
        Diet diet = dietMapper.toEntity(member,dietReqDto);
        return dietRepository.save(diet);
    }

    public DietResDto findById(Long id)throws TheFitBizException {
        Diet diet = dietRepository.findById(id)
                .orElseThrow(() -> new TheFitBizException(ErrorCode.NOT_FOUND_DIET));
        Member member = memberRepository.findById(diet.getMember().getId()).orElseThrow();
        return dietMapper.toDto(member, diet);
    }

    public Diet update(Long id, DietReqDto dietReqDto)throws TheFitBizException {
        Diet diet = dietRepository.findById(id)
                .orElseThrow(() -> new TheFitBizException(ErrorCode.NOT_FOUND_DIET));
        diet.update(dietReqDto);
        return diet;
    }

    public void delete(Long id) {
        dietRepository.deleteById(id);
    }

    public List<DietResDto> findAll() {
        List<Diet> diets = dietRepository.findAll();
        List<DietResDto> dietResDtos = new ArrayList<>();
        for(Diet diet :diets){
            dietResDtos.add(dietMapper.toDto(diet.getMember(), diet));
        }
        return dietResDtos;
    }
}