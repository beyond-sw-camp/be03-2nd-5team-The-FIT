package com.example.TheFit.workout.service;

import com.example.TheFit.totalworkouts.domain.TotalWorkOuts;
import com.example.TheFit.totalworkouts.repository.TotalWorkOutsRepository;
import com.example.TheFit.workout.domain.WorkOut;
import com.example.TheFit.workout.dto.WorkOutReqDto;
import com.example.TheFit.workout.dto.WorkOutResDto;
import com.example.TheFit.workout.repository.WorkOutRepository;
import com.example.TheFit.workoutlist.domain.WorkOutList;
import com.example.TheFit.workoutlist.repository.WorkOutListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkOutService {
    private final WorkOutRepository workOutRepository;
    private final WorkOutListRepository workOutListRepository;
    private final TotalWorkOutsRepository totalWorkOutsRepository;

    @Autowired
    public WorkOutService(WorkOutRepository workOutRepository, WorkOutListRepository workOutListRepository, TotalWorkOutsRepository totalWorkOutsRepository) {
        this.workOutRepository = workOutRepository;
        this.workOutListRepository = workOutListRepository;
        this.totalWorkOutsRepository = totalWorkOutsRepository;
    }

    public void create(WorkOutReqDto workOutReqDto) {
        WorkOutList workOutList = workOutListRepository.findById(workOutReqDto.getWorkOutListId())
                .orElseThrow(() -> new EntityNotFoundException("WorkOutList not found"));
        TotalWorkOuts totalWorkOuts = totalWorkOutsRepository.findById(workOutReqDto.getTotalWorkOutsId())
                .orElseThrow(() -> new EntityNotFoundException("TotalWorkOuts not found"));
        WorkOut workOut = WorkOut.builder()
                .workOutList(workOutList)
                .totalWorkOuts(totalWorkOuts)
                .sets(workOutReqDto.getSets())
                .weight(workOutReqDto.getWeight())
                .reps(workOutReqDto.getReps())
                .restTime(workOutReqDto.getRestTime())
                .performance(workOutReqDto.getPerformance())
                .build();
        workOutRepository.save(workOut);
    }

    public List<WorkOutResDto> findAll() {
        List<WorkOut> workOuts = workOutRepository.findAll();
        List<WorkOutResDto> workOutResDtos = new ArrayList<>();
        for (WorkOut workOut : workOuts) {
            WorkOutResDto workOutResDto = WorkOutResDto.builder()
                    .id(workOut.getId())
                    .workOutListId(workOut.getWorkOutList() != null ? workOut.getWorkOutList().getId() : null)
                    .totalWorkOutsId(workOut.getTotalWorkOuts() != null ? workOut.getTotalWorkOuts().getId() : null)
                    .sets(workOut.getSets())
                    .weight(workOut.getWeight())
                    .reps(workOut.getReps())
                    .restTime(workOut.getRestTime())
                    .performance(workOut.getPerformance())
                    .workOutStatus(workOut.getWorkOutStatus())
                    .build();
            workOutResDtos.add(workOutResDto);
        }
        return workOutResDtos;
    }
        public WorkOut update(Long id, WorkOutReqDto workOutReqDto) {
            WorkOut workOutUpdate = workOutRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("not found"));
            workOutUpdate.update(workOutReqDto);
            return workOutRepository.save(workOutUpdate);
        }

        public void delete(Long id) {
            workOutRepository.deleteById(id);
        }
    }