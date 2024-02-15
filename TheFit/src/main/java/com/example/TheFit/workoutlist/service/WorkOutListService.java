package com.example.TheFit.workoutlist.service;

import com.example.TheFit.member.domain.Member;
import com.example.TheFit.member.repository.MemberRepository;
import com.example.TheFit.workoutlist.domain.WorkOutList;
import com.example.TheFit.workoutlist.dto.WorkOutListDto;
import com.example.TheFit.workoutlist.repository.WorkOutListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


@Service
public class WorkOutListService {
    private final WorkOutListRepository workOutListRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public WorkOutListService(WorkOutListRepository workOutListRepository, MemberRepository memberRepository) {
        this.workOutListRepository = workOutListRepository;
        this.memberRepository = memberRepository;
    }
    public void create(WorkOutListDto workOutListDto) {
        Member member = memberRepository.findById(workOutListDto.getMemberId())
                .orElseThrow(()->new EntityNotFoundException("not found"));
        WorkOutList workOutList = WorkOutList.builder()
                .member(member)
                .workOutDate(workOutListDto.getWorkOutDate())
                .build();
        workOutListRepository.save(workOutList);
    }
    public void delete(Long id) {
        workOutListRepository.deleteById(id);
    }
}

