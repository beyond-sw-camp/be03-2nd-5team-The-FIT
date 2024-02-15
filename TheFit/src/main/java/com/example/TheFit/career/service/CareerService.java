package com.example.TheFit.career.service;


import com.example.TheFit.career.domain.Career;
import com.example.TheFit.career.dto.CareerDto;
import com.example.TheFit.career.repository.CareerRepository;
import com.example.TheFit.trainer.domain.Trainer;
import com.example.TheFit.trainer.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CareerService {
    private final CareerRepository careerRepository;
    private final TrainerRepository trainerRepository;
    @Autowired
    public CareerService(CareerRepository careerRepository, TrainerRepository trainerRepository) {
        this.careerRepository = careerRepository;
        this.trainerRepository = trainerRepository;
    }


    public void create(CareerDto careerDto) {
        Trainer trainer = trainerRepository.findById(careerDto.getTrainerId())
                .orElseThrow(()->new EntityNotFoundException("not found"));
        Career career = Career.builder()
                .trainer(trainer)
                .awards(careerDto.getAwards())
                .license(careerDto.getLicense())
                .work(careerDto.getWork())
                .build();
        careerRepository.save(career);
    }

    public List<CareerDto> findAll() {
        List<Career> careers = careerRepository.findAll();
        List<CareerDto> careerDtos = new ArrayList<>();
        for (Career career : careers) {
            CareerDto careerDto = CareerDto.builder()
                    .awards(career.getAwards())
                    .license(career.getLicense())
                    .work(career.getWork())
                    .build();
            careerDtos.add(careerDto);
        }
        return careerDtos;
    }

    public Career update(Long id, CareerDto careerDto) {
        Career careerUpdate = careerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found"));
        careerUpdate.update(careerDto);
        return careerRepository.save(careerUpdate);
    }

    public void delete(Long id) {
        careerRepository.deleteById(id);
    }
}

