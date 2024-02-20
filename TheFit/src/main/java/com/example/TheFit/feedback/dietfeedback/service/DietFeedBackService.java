package com.example.TheFit.feedback.dietfeedback.service;

import com.example.TheFit.common.ErrorCode;
import com.example.TheFit.common.TheFitBizException;
import com.example.TheFit.feedback.FeedBackMapper;
import com.example.TheFit.feedback.dietfeedback.domain.DietFeedBack;
import com.example.TheFit.feedback.dietfeedback.dto.DietFeedBackReqDto;
import com.example.TheFit.feedback.dietfeedback.dto.DietFeedBackResDto;
import com.example.TheFit.feedback.dietfeedback.repository.DietFeedBackRepository;
import com.example.TheFit.diet.domain.Diet;
import com.example.TheFit.diet.repository.DietRepository;
import com.example.TheFit.user.trainer.domain.Trainer;
import com.example.TheFit.user.trainer.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DietFeedBackService {

    private final DietFeedBackRepository dietFeedBackRepository;
    private final FeedBackMapper feedBackMapper = FeedBackMapper.INSTANCE;
    private final DietRepository dietRepository;
    private final TrainerRepository trainerRepository;

    @Autowired
    public DietFeedBackService(DietFeedBackRepository dietFeedBackRepository, DietRepository dietRepository, TrainerRepository trainerRepository) {
        this.dietFeedBackRepository = dietFeedBackRepository;
        this.dietRepository = dietRepository;
        this.trainerRepository = trainerRepository;
    }

    public DietFeedBack create(DietFeedBackReqDto dietFeedBackReqDto) {
        Diet diet = dietRepository.findById(dietFeedBackReqDto.getDietId()).orElseThrow(()->new TheFitBizException(ErrorCode.NOT_FOUND_DIET));
        Trainer trainer = trainerRepository.findById(dietFeedBackReqDto.getTrainerId()).orElseThrow(()->new TheFitBizException(ErrorCode.NOT_FOUND_TRAINER));
        DietFeedBack dietFeedback = feedBackMapper.toEntity(trainer,diet,dietFeedBackReqDto);
        return dietFeedBackRepository.save(dietFeedback);
    }

    public List<DietFeedBackResDto> findAll() {
        return dietFeedBackRepository.findAll().stream()
                .map(feedBackMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public DietFeedBack update(Long id, DietFeedBackReqDto dietFeedBackReqDto) {
        DietFeedBack dietFeedBack = dietFeedBackRepository.findById(id)
                .orElseThrow(() -> new TheFitBizException(ErrorCode.NOT_FOUND_DIET_FEEDBACK));
        Diet diet = dietFeedBack.getDiet();
        Trainer trainer = dietFeedBack.getTrainer();
        dietFeedBack.update(diet,trainer,dietFeedBackReqDto);
        return dietFeedBack;
    }

    public void delete(Long id) {
        dietFeedBackRepository.deleteById(id);
    }
}