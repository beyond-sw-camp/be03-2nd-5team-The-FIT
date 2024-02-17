package com.example.TheFit.workoutfeedback.service;

import com.example.TheFit.trainer.domain.Trainer;
import com.example.TheFit.trainer.repository.TrainerRepository;
import com.example.TheFit.workoutfeedback.domain.WorkOutFeedBack;
import com.example.TheFit.workoutfeedback.dto.WorkOutFeedBackReqDto;
import com.example.TheFit.workoutfeedback.dto.WorkOutFeedBackResDto;
import com.example.TheFit.workoutfeedback.repository.WorkOutFeedBackRepository;
import com.example.TheFit.workoutlist.domain.WorkOutList;
import com.example.TheFit.workoutlist.repository.WorkOutListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WorkOutFeedBackService {

    private final WorkOutFeedBackRepository workOutFeedBackRepository;
    private final WorkOutListRepository workOutListRepository;
    private final TrainerRepository trainerRepository;

    @Autowired
    public WorkOutFeedBackService(WorkOutFeedBackRepository workOutFeedBackRepository, WorkOutListRepository workOutListRepository, TrainerRepository trainerRepository) {
        this.workOutFeedBackRepository = workOutFeedBackRepository;
        this.workOutListRepository = workOutListRepository;
        this.trainerRepository = trainerRepository;
    }
    public void create(WorkOutFeedBackReqDto workOutFeedBackReqDto) {
        WorkOutList workOutList = workOutListRepository.findById(workOutFeedBackReqDto.getWorkOutListId())
                .orElseThrow(()->new EntityNotFoundException("not found"));
        Trainer trainer = trainerRepository.findById(workOutFeedBackReqDto.getTrainerId())
                .orElseThrow(()->new EntityNotFoundException("not found"));
        WorkOutFeedBack workOutFeedback = WorkOutFeedBack.builder()
                .workOutList(workOutList)
                .trainer(trainer)
                .feedBack(workOutFeedBackReqDto.getFeedBack())
                .rating(workOutFeedBackReqDto.getRating())
                .build();
        workOutFeedBackRepository.save(workOutFeedback);
    }
    public List<WorkOutFeedBackResDto> findAll() {
        List<WorkOutFeedBack> workOutFeedBacks = workOutFeedBackRepository.findAll();
        List<WorkOutFeedBackResDto> workOutFeedBackDtos = new ArrayList<>();
        for (WorkOutFeedBack workOutFeedback : workOutFeedBacks) {
            WorkOutFeedBackResDto workOutFeedBackResDto = WorkOutFeedBackResDto.builder()
                    .id(workOutFeedback.getId())
                    .workOutListId(workOutFeedback.getWorkOutList() != null ? workOutFeedback.getWorkOutList().getId() : null)
                    .trainerId(workOutFeedback.getTrainer() != null ? workOutFeedback.getTrainer().getId() : null)
                    .feedBack(workOutFeedback.getFeedBack())
                    .rating(workOutFeedback.getRating())
                    .build();
            workOutFeedBackDtos.add(workOutFeedBackResDto);
        }
        return workOutFeedBackDtos;
    }
    public WorkOutFeedBack update(Long id, WorkOutFeedBackReqDto workOutFeedBackReqDto) {
        WorkOutFeedBack workOutFeedBack = workOutFeedBackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found"));
        workOutFeedBack.update(workOutFeedBackReqDto);
        return workOutFeedBackRepository.save(workOutFeedBack);
    }
    public void delete(Long id) {
        workOutFeedBackRepository.deleteById(id);
    }
}