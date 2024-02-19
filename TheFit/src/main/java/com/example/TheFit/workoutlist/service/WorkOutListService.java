package com.example.TheFit.workoutlist.service;

import com.example.TheFit.user.member.domain.Member;
import com.example.TheFit.user.member.repository.MemberRepository;
import com.example.TheFit.workoutlist.domain.WorkOutList;
import com.example.TheFit.workoutlist.dto.WorkOutListReqDto;
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

    public void create(WorkOutListReqDto workOutListReqDto) {
        Member member = memberRepository.findById(workOutListReqDto.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        WorkOutList workOutList = WorkOutList.builder()
                .member(member)
                .workOutDate(workOutListReqDto.getWorkOutDate())
                .build();
        workOutListRepository.save(workOutList);
    }

    public void delete(Long id) {
        workOutListRepository.deleteById(id);
    }
}
