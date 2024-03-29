package com.example.TheFit.feedback.workoutfeedback.service;

import com.example.TheFit.common.ErrorCode;
import com.example.TheFit.common.TheFitBizException;
import com.example.TheFit.feedback.FeedBackMapper;
import com.example.TheFit.feedback.workoutfeedback.repository.WorkOutFeedBackRepository;
import com.example.TheFit.user.trainer.domain.Trainer;
import com.example.TheFit.user.trainer.repository.TrainerRepository;
import com.example.TheFit.feedback.workoutfeedback.domain.WorkOutFeedBack;
import com.example.TheFit.feedback.workoutfeedback.dto.WorkOutFeedBackReqDto;
import com.example.TheFit.feedback.workoutfeedback.dto.WorkOutFeedBackResDto;
import com.example.TheFit.workoutlist.domain.WorkOutList;
import com.example.TheFit.workoutlist.repository.WorkOutListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WorkOutFeedBackService {

    private final WorkOutFeedBackRepository workOutFeedBackRepository;
    private final FeedBackMapper feedBackMapper = FeedBackMapper.INSTANCE;
    private final TrainerRepository trainerRepository;

    @Autowired
    public WorkOutFeedBackService(WorkOutFeedBackRepository workOutFeedBackRepository, WorkOutListRepository workOutListRepository, TrainerRepository trainerRepository) {
        this.workOutFeedBackRepository = workOutFeedBackRepository;
        this.trainerRepository = trainerRepository;
    }
    public WorkOutFeedBack create(WorkOutFeedBackReqDto workOutFeedBackReqDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Trainer trainer = trainerRepository.findByEmail(authentication.getName())
                .orElseThrow(()->new TheFitBizException(ErrorCode.NOT_FOUND_TRAINER));
        WorkOutFeedBack workOutFeedback = feedBackMapper.toEntity(trainer,workOutFeedBackReqDto);
        return workOutFeedBackRepository.save(workOutFeedback);
    }
    public List<WorkOutFeedBackResDto> findAll() {
        List<WorkOutFeedBack> workOutFeedBacks = workOutFeedBackRepository.findAll();
        List<WorkOutFeedBackResDto> workOutFeedBackDtos = new ArrayList<>();
        for (WorkOutFeedBack workOutFeedBack : workOutFeedBacks) {
            workOutFeedBackDtos.add(feedBackMapper.toDto(workOutFeedBack));
        }
        return workOutFeedBackDtos;
    }
    public WorkOutFeedBack update(Long id, WorkOutFeedBackReqDto workOutFeedBackReqDto) {
        WorkOutFeedBack workOutFeedBack = workOutFeedBackRepository.findById(id)
                .orElseThrow(()->new TheFitBizException(ErrorCode.NOT_FOUND_WORKOUT_FEEDBACK));
        workOutFeedBack.update(workOutFeedBackReqDto);
        return workOutFeedBackRepository.save(workOutFeedBack);
    }
    public void delete(Long id) {
        workOutFeedBackRepository.deleteById(id);
    }

    public WorkOutFeedBackResDto findFeedback(String date) {
        WorkOutFeedBack workOutFeedBack= workOutFeedBackRepository.findByUploadDate(date).orElseThrow(
                ()-> new TheFitBizException(ErrorCode.NOT_FOUND_WORKOUT_FEEDBACK)
        );
        return feedBackMapper.toDto(workOutFeedBack);
    }
}