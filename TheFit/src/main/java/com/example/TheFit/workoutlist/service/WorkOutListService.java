package com.example.TheFit.workoutlist.service;

import com.example.TheFit.workoutlist.domain.WorkOutList;
import com.example.TheFit.workoutlist.dto.WorkOutListDto;
import com.example.TheFit.workoutlist.repository.WorkOutListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WorkOutListService {
    private final WorkOutListRepository workOutListRepository;

    @Autowired
    public WorkOutListService(WorkOutListRepository workOutListRepository) {
        this.workOutListRepository = workOutListRepository;
    }
    public void create(WorkOutListDto workOutListDto) {
        WorkOutList workOutList = WorkOutList.builder()
                .workOutDate(workOutListDto.getWorkOutDate())
                .build();
        workOutListRepository.save(workOutList);
    }
    public void delete(Long id) {
        workOutListRepository.deleteById(id);
    }
}

