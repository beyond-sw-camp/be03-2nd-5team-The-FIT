package com.example.TheFit.workout.service;


import com.example.TheFit.workout.domain.WorkOut;
import com.example.TheFit.workout.dto.WorkOutDto;
import com.example.TheFit.workout.repository.WorkOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkOutService {
    private final WorkOutRepository workOutRepository;

    @Autowired
    public WorkOutService(WorkOutRepository workOutRepository) {
        this.workOutRepository = workOutRepository;
    }
    public void create(WorkOutDto workOutDto) {
        WorkOut workOut = WorkOut.builder()
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

