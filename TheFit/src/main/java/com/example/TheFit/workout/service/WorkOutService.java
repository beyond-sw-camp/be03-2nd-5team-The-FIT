package com.example.TheFit.workout.service;


import com.example.TheFit.totalworkouts.domain.TotalWorkOuts;
import com.example.TheFit.totalworkouts.repository.TotalWorkOutsRepository;
import com.example.TheFit.workout.domain.WorkOut;
import com.example.TheFit.workout.dto.WorkOutDto;
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
    public void create(WorkOutDto workOutDto) {
        WorkOutList workOutList = workOutListRepository.findById(workOutDto.getWorkOutListId())
                .orElseThrow(()->new EntityNotFoundException("not found"));
        TotalWorkOuts totalWorkOuts = totalWorkOutsRepository.findById(workOutDto.getTotalWorkOutsId())
                .orElseThrow(()->new EntityNotFoundException("not found"));
        WorkOut workOut = WorkOut.builder()
                .workOutList(workOutList)
                .totalWorkOuts(totalWorkOuts)
                .sets(workOutDto.getSets())
                .weight(workOutDto.getWeight())
                .reps(workOutDto.getReps())
                .restTime(workOutDto.getRestTime())
                .performance(workOutDto.getPerformance())
                .build();
        workOutRepository.save(workOut);
    }

    public List<WorkOutDto> findAll() {
        List<WorkOut> workOuts = workOutRepository.findAll();
        List<WorkOutDto> workOutDtos = new ArrayList<>();
        for (WorkOut workOut : workOuts) {
            WorkOutDto workOutDto = WorkOutDto.builder()
                    .workOutListId(workOut.getWorkOutList() != null ? workOut.getWorkOutList().getId() : null)
                    .totalWorkOutsId(workOut.getTotalWorkOuts() != null ? workOut.getTotalWorkOuts().getId() : null)
                    .sets(workOut.getSets())
                    .weight(workOut.getWeight())
                    .reps(workOut.getReps())
                    .restTime(workOut.getRestTime())
                    .performance(workOut.getPerformance())
                    .build();
            workOutDtos.add(workOutDto);
        }
        return workOutDtos;
    }

    public WorkOut update(Long id, WorkOutDto workOutDto) {
        WorkOut workOutUpdate = workOutRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found"));
        workOutUpdate.update(workOutDto);
        return workOutRepository.save(workOutUpdate);
    }

    public void delete(Long id) {
        workOutRepository.deleteById(id);
    }
}

