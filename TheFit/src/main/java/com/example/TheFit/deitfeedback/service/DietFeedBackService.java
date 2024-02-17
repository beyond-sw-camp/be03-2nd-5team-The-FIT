package com.example.TheFit.deitfeedback.service;

import com.example.TheFit.deitfeedback.domain.DietFeedBack;
import com.example.TheFit.deitfeedback.dto.DietFeedBackReqDto;
import com.example.TheFit.deitfeedback.dto.DietFeedBackResDto;
import com.example.TheFit.deitfeedback.repository.DietFeedBackRepository;
import com.example.TheFit.diet.domain.Diet;
import com.example.TheFit.diet.repository.DietRepository;
import com.example.TheFit.trainer.domain.Trainer;
import com.example.TheFit.trainer.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DietFeedBackService {

    private final DietFeedBackRepository dietFeedBackRepository;
    private final DietRepository dietRepository;
    private final TrainerRepository trainerRepository;

    @Autowired
    public DietFeedBackService(DietFeedBackRepository dietFeedBackRepository, DietRepository dietRepository, TrainerRepository trainerRepository) {
        this.dietFeedBackRepository = dietFeedBackRepository;
        this.dietRepository = dietRepository;
        this.trainerRepository = trainerRepository;
    }

    public void create(DietFeedBackReqDto dietFeedBackReqDto) {
        Diet diet = dietRepository.findById(dietFeedBackReqDto.getDietId())
                .orElseThrow(()->new EntityNotFoundException("not found"));
        Trainer trainer = trainerRepository.findById(dietFeedBackReqDto.getTrainerId())
                .orElseThrow(()->new EntityNotFoundException("not found"));
        DietFeedBack dietFeedback = DietFeedBack.builder()
                .diet(diet)
                .trainer(trainer)
                .feedBack(dietFeedBackReqDto.getFeedBack())
                .rating(dietFeedBackReqDto.getRating())
                .build();
        dietFeedBackRepository.save(dietFeedback);
    }
    public List<DietFeedBackResDto> findAll() {
        List<DietFeedBack> dietFeedBacks = dietFeedBackRepository.findAll();
        return dietFeedBacks.stream().map(dietFeedBack -> DietFeedBackResDto.builder()
                .id(dietFeedBack.getId())
                .dietId(dietFeedBack.getDiet() != null ? dietFeedBack.getDiet().getId() : null)
                .trainerId(dietFeedBack.getTrainer() != null ? dietFeedBack.getTrainer().getId() : null)
                .feedBack(dietFeedBack.getFeedBack())
                .rating(dietFeedBack.getRating())
                .build()).collect(Collectors.toList());
    }
    public DietFeedBack update(Long id, DietFeedBackReqDto dietFeedBackReqDto) {
        DietFeedBack dietFeedBack = dietFeedBackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found"));
        dietFeedBack.update(dietFeedBackReqDto);
        return dietFeedBackRepository.save(dietFeedBack);
    }
    public void delete(Long id) {
        dietFeedBackRepository.deleteById(id);
    }
}