package com.example.TheFit.workoutfeedback.service;

import com.example.TheFit.user.trainer.domain.Trainer;
import com.example.TheFit.user.trainer.repository.TrainerRepository;
import com.example.TheFit.workoutfeedback.domain.WorkOutFeedBack;
import com.example.TheFit.workoutfeedback.dto.WorkOutFeedBackReqDto;
import com.example.TheFit.workoutfeedback.dto.WorkOutFeedBackResDto;
import com.example.TheFit.workoutfeedback.mapper.WorkOutFeedBackMapper;
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
    private final WorkOutFeedBackMapper workOutFeedBackMapper = WorkOutFeedBackMapper.INSTANCE;
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
        for (WorkOutFeedBack workOutFeedBack : workOutFeedBacks) {
            WorkOutFeedBackResDto workOutFeedBackResDto = WorkOutFeedBackResDto.builder()
                    .id(workOutFeedBack.getId())
                    .workOutListId(workOutFeedBack.getWorkOutList() != null ? workOutFeedBack.getWorkOutList().getId() : null)
                    .trainerId(workOutFeedBack.getTrainer() != null ? workOutFeedBack.getTrainer().getId() : null)
                    .feedBack(workOutFeedBack.getFeedBack())
                    .rating(workOutFeedBack.getRating())
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