package com.example.TheFit.workout.service;

import com.example.TheFit.common.ErrorCode;
import com.example.TheFit.common.TheFitBizException;
import com.example.TheFit.diet.domain.Diet;
import com.example.TheFit.diet.dto.DietResDto;
import com.example.TheFit.totalworkouts.domain.TotalWorkOuts;
import com.example.TheFit.totalworkouts.repository.TotalWorkOutsRepository;
import com.example.TheFit.user.member.domain.Member;
import com.example.TheFit.workout.domain.WorkOut;
import com.example.TheFit.workout.dto.WorkOutReqDto;
import com.example.TheFit.workout.dto.WorkOutResDto;
import com.example.TheFit.workout.mapper.WorkOutMapper;
import com.example.TheFit.workout.repository.WorkOutRepository;
import com.example.TheFit.workoutlist.domain.WorkOutList;
import com.example.TheFit.workoutlist.repository.WorkOutListRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class WorkOutService {
    private final WorkOutRepository workOutRepository;
    private final WorkOutListRepository workOutListRepository;
    private final TotalWorkOutsRepository totalWorkOutsRepository;
    private final WorkOutMapper workOutMapper = WorkOutMapper.INSTANCE;

    @Autowired
    public WorkOutService(WorkOutRepository workOutRepository, WorkOutListRepository workOutListRepository, TotalWorkOutsRepository totalWorkOutsRepository) {
        this.workOutRepository = workOutRepository;
        this.workOutListRepository = workOutListRepository;
        this.totalWorkOutsRepository = totalWorkOutsRepository;
    }

    public WorkOut create(WorkOutReqDto workOutReqDto) {
        TotalWorkOuts totalWorkOuts = totalWorkOutsRepository.findById(workOutReqDto.getTotalWorkOutsId()).orElseThrow(() -> new TheFitBizException(ErrorCode.NOT_FOUND_TOTALWORKOUT));
        WorkOutList workOutList = workOutListRepository.findById(workOutReqDto.getWorkOutListId()).orElseThrow(() -> new TheFitBizException(ErrorCode.NOT_FOUND_WORKOUTLIST));
        ;
        WorkOut workOut = workOutMapper.toEntity(totalWorkOuts, workOutList, workOutReqDto);
        return workOutRepository.save(workOut);
    }

    public List<WorkOutResDto> findAll() {
        List<WorkOut> workOuts = workOutRepository.findAll();
        List<WorkOutResDto> workOutResDtos = new ArrayList<>();
        for (WorkOut workOut : workOuts) {
            workOutResDtos.add(workOutMapper.toDto(workOut));
        }
        return workOutResDtos;
    }

    public WorkOutResDto findById(Long id) throws TheFitBizException {
        WorkOut workOut = workOutRepository.findById(id)
                .orElseThrow(() -> new TheFitBizException(ErrorCode.NOT_FOUND_WORKOUT));
        WorkOutList workOutList = workOutListRepository.findById(workOut.getWorkOutList().getId()).orElseThrow();
        return workOutMapper.toDto(workOut);
    }

    public List<WorkOutResDto> findByMemberId(Long id) throws TheFitBizException {
        List<WorkOutList> workOutLists = workOutListRepository.findByMemberId(id).orElseThrow(() -> new TheFitBizException(ErrorCode.NOT_FOUND_WORKOUTLIST));
        List<WorkOut> workOuts = workOutRepository.findAll();
        List<WorkOutResDto> workOutResDtos = new ArrayList<>();
        for(WorkOutList workOutList : workOutLists){
            for(WorkOut workOut : workOuts){
                if(workOutList.getId().equals(workOut.getWorkOutList().getId())){
                    workOutResDtos.add(workOutMapper.toDto(workOut));
                }
            }
        }
        return workOutResDtos;
    }

    public WorkOut update(Long id, WorkOutReqDto workOutReqDto) {
        WorkOut workOutUpdate = workOutRepository.findById(id)
                .orElseThrow(() -> new TheFitBizException(ErrorCode.NOT_FOUND_WORKOUT));
        workOutUpdate.update(workOutReqDto);
        return workOutUpdate;
    }

    public void delete(Long id) {
        workOutRepository.deleteById(id);
    }


}