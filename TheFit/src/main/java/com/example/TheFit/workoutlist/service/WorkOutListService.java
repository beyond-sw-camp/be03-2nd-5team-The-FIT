package com.example.TheFit.workoutlist.service;

import com.example.TheFit.common.ErrorCode;
import com.example.TheFit.common.TheFitBizException;
import com.example.TheFit.diet.domain.Diet;
import com.example.TheFit.diet.dto.DietResDto;
import com.example.TheFit.user.member.domain.Member;
import com.example.TheFit.user.member.repository.MemberRepository;
import com.example.TheFit.workoutlist.domain.WorkOutList;
import com.example.TheFit.workoutlist.dto.WorkOutListReqDto;
import com.example.TheFit.workoutlist.dto.WorkOutListResDto;
import com.example.TheFit.workoutlist.mapper.WorkOutListMapper;
import com.example.TheFit.workoutlist.repository.WorkOutListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkOutListService {
    private final WorkOutListRepository workOutListRepository;
    private final MemberRepository memberRepository;
    private final WorkOutListMapper workOutListMapper;
    @Autowired
    public WorkOutListService(WorkOutListRepository workOutListRepository, MemberRepository memberRepository, WorkOutListMapper workOutListMapper) {
        this.workOutListRepository = workOutListRepository;
        this.memberRepository = memberRepository;
        this.workOutListMapper = workOutListMapper;
    }

    public WorkOutList create(WorkOutListReqDto workOutListReqDto) throws TheFitBizException {
        Member member = memberRepository.findById(workOutListReqDto.getMemberId())
                .orElseThrow(() -> new TheFitBizException(ErrorCode.NOT_FOUND_MEMBER));
        WorkOutList workOutList = workOutListMapper.toEntity(member, workOutListReqDto);
        return workOutListRepository.save(workOutList);
    }

    public void delete(Long id) {
        workOutListRepository.deleteById(id);
    }

    public List<WorkOutListResDto> findAll() {
        List<WorkOutList> workOutLists = workOutListRepository.findAll();
        List<WorkOutListResDto> workOutListResDtos = new ArrayList<>();
        for(WorkOutList workOutList : workOutLists){
            workOutListResDtos.add(workOutListMapper.toDto(workOutList));
        }
        return workOutListResDtos;
    }

    public WorkOutListResDto findByMemberEmailAndWorkOutDate(String email, String inputDate) {
        Long memberId = memberRepository.findByEmail(email).orElseThrow(
                () -> new TheFitBizException(ErrorCode.NOT_FOUND_MEMBER)
        ).getId();
        LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ISO_LOCAL_DATE);
        List<WorkOutList> workOutLists = workOutListRepository.findByMemberIdAndWorkOutDate(memberId, date)
                .orElseThrow(()-> new TheFitBizException(ErrorCode.NOT_FOUND_WORKOUTLIST));
        return workOutListMapper.toDto(workOutLists.get(0));

    }
}
