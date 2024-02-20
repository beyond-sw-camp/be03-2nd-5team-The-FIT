package com.example.TheFit.dietfeedback.service;

import com.example.TheFit.dietfeedback.domain.DietFeedBack;
import com.example.TheFit.dietfeedback.dto.DietFeedBackReqDto;
import com.example.TheFit.dietfeedback.dto.DietFeedBackResDto;
import com.example.TheFit.dietfeedback.mapper.DietFeedBackMapper;
import com.example.TheFit.dietfeedback.repository.DietFeedBackRepository;
import com.example.TheFit.diet.domain.Diet;
import com.example.TheFit.diet.repository.DietRepository;
import com.example.TheFit.user.trainer.domain.Trainer;
import com.example.TheFit.user.trainer.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DietFeedBackService {

    private final DietFeedBackRepository dietFeedBackRepository;
    private final DietFeedBackMapper dietFeedBackMapper = DietFeedBackMapper.INSTANCE;
    private final DietRepository dietRepository;
    private final TrainerRepository trainerRepository;

    @Autowired
    public DietFeedBackService(DietFeedBackRepository dietFeedBackRepository, DietRepository dietRepository, TrainerRepository trainerRepository) {
        this.dietFeedBackRepository = dietFeedBackRepository;
        this.dietRepository = dietRepository;
        this.trainerRepository = trainerRepository;
    }

    public DietFeedBack create(DietFeedBackReqDto dietFeedBackReqDto) {
        DietFeedBack dietFeedback = dietFeedBackMapper.toEntity(dietFeedBackReqDto);
        return dietFeedBackRepository.save(dietFeedback);
    }

    public List<DietFeedBackResDto> findAll() {
        return dietFeedBackRepository.findAll().stream()
                .map(dietFeedBackMapper::toDto)
                .collect(Collectors.toList());
    }

    public void update(Long id, DietFeedBackReqDto dietFeedBackReqDto) {
        DietFeedBack dietFeedBack = dietFeedBackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DietFeedback not found"));
        Diet diet = dietRepository.findById(dietFeedBackReqDto.getDietId())
                .orElseThrow(() -> new EntityNotFoundException("Diet not found"));
        Trainer trainer = trainerRepository.findById(dietFeedBackReqDto.getTrainerId())
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found"));
        dietFeedBackMapper.update(dietFeedBackReqDto, dietFeedBack);
        dietFeedBack.setDiet(diet);
        dietFeedBack.setTrainer(trainer);
        dietFeedBackRepository.save(dietFeedBack);
    }

    public void delete(Long id) {
        dietFeedBackRepository.deleteById(id);
    }
}