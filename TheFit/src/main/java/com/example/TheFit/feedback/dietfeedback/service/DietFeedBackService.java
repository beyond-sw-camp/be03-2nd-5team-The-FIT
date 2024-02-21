package com.example.TheFit.feedback.dietfeedback.service;

import com.example.TheFit.common.ErrorCode;
import com.example.TheFit.common.TheFitBizException;
import com.example.TheFit.feedback.FeedBackMapper;
import com.example.TheFit.feedback.dietfeedback.domain.DietFeedBack;
import com.example.TheFit.feedback.dietfeedback.dto.DietFeedBackReqDto;
import com.example.TheFit.feedback.dietfeedback.dto.DietFeedBackResDto;
import com.example.TheFit.feedback.dietfeedback.repository.DietFeedBackRepository;
import com.example.TheFit.diet.repository.DietRepository;
import com.example.TheFit.user.trainer.domain.Trainer;
import com.example.TheFit.user.trainer.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DietFeedBackService {

    private final DietFeedBackRepository dietFeedBackRepository;
    private final FeedBackMapper feedBackMapper = FeedBackMapper.INSTANCE;
    private final TrainerRepository trainerRepository;

    @Autowired
    public DietFeedBackService(DietFeedBackRepository dietFeedBackRepository, TrainerRepository trainerRepository) {
        this.dietFeedBackRepository = dietFeedBackRepository;
        this.trainerRepository = trainerRepository;
    }

    public DietFeedBack create(DietFeedBackReqDto dietFeedBackReqDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(dietFeedBackRepository.findByUploadDate(dietFeedBackReqDto.getUploadDate()).isPresent()){
            throw new TheFitBizException(ErrorCode.FEEDBACK_DATE_DUPLICATE);
        }
        Trainer trainer = trainerRepository.findByEmail(authentication.getName()).orElseThrow(()->new TheFitBizException(ErrorCode.NOT_FOUND_TRAINER));
        DietFeedBack dietFeedback = feedBackMapper.toEntity(trainer,dietFeedBackReqDto);
        return dietFeedBackRepository.save(dietFeedback);
    }

    public List<DietFeedBackResDto> findAll() {
        List<DietFeedBack> dietFeedBacks = dietFeedBackRepository.findAll();
        List<DietFeedBackResDto> dietFeedBackResDtoList = new ArrayList<>();
        for(DietFeedBack dietFeedBack : dietFeedBacks){
            dietFeedBackResDtoList.add(feedBackMapper.toDto(dietFeedBack.trainer.getName(),dietFeedBack));
        }
        return dietFeedBackResDtoList;
    }

    @Transactional
    public DietFeedBack update(Long id, DietFeedBackReqDto dietFeedBackReqDto) {
        DietFeedBack dietFeedBack = dietFeedBackRepository.findById(id)
                .orElseThrow(() -> new TheFitBizException(ErrorCode.NOT_FOUND_DIET_FEEDBACK));
        Trainer trainer = dietFeedBack.getTrainer();
        dietFeedBack.update(trainer,dietFeedBackReqDto);
        return dietFeedBack;
    }

    public void delete(Long id) {
        dietFeedBackRepository.deleteById(id);
    }

    public DietFeedBackResDto findFeedBack() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Trainer trainer = trainerRepository.findByEmail(authentication.getName()).orElseThrow(
                () -> new TheFitBizException(ErrorCode.NOT_FOUND_TRAINER)
        );
        DietFeedBack dietFeedBack = dietFeedBackRepository.findByTrainerId(trainer.getId()).orElseThrow(
                ()->new TheFitBizException(ErrorCode.NOT_FOUND_DIET_FEEDBACK));
        return feedBackMapper.toDto(trainer.name,dietFeedBack);
    }
}