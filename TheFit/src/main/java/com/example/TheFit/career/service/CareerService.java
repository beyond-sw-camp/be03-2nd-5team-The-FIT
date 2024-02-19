package com.example.TheFit.career.service;


import com.example.TheFit.career.domain.Career;
import com.example.TheFit.career.dto.CareerReqDto;
import com.example.TheFit.career.dto.CareerResDto;
import com.example.TheFit.career.mapper.CareerMapper;
import com.example.TheFit.career.repository.CareerRepository;
import com.example.TheFit.trainer.domain.Trainer;
import com.example.TheFit.trainer.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CareerService {
    private final CareerRepository careerRepository;
    private final TrainerRepository trainerRepository;
    private final CareerMapper careerMapper = CareerMapper.INSTANCE;

    @Autowired
    public CareerService(CareerRepository careerRepository, TrainerRepository trainerRepository) {
        this.careerRepository = careerRepository;
        this.trainerRepository = trainerRepository;
    }

    public void create(CareerReqDto careerReqDto) {
        Trainer trainer = trainerRepository.findById(careerReqDto.getTrainerId())
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found"));
        Career career = careerMapper.toEntity(careerReqDto);
        career.setTrainer(trainer);
        careerRepository.save(career);
    }

    public List<CareerResDto> findAll() {
        List<Career> careers = careerRepository.findAll();
        return careers.stream()
                .map(careerMapper::toDto)
                .collect(Collectors.toList());
    }

    public Career update(Long id, CareerReqDto careerReqDto) {
        Career career = careerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Career not found"));
        Trainer trainer = trainerRepository.findById(careerReqDto.getTrainerId())
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found"));
        careerMapper.update(careerReqDto, career);
        career.setTrainer(trainer);
        return careerRepository.save(career);
    }

    public void delete(Long id) {
        careerRepository.deleteById(id);
    }
}

