package com.example.TheFit.career.service;


import com.example.TheFit.career.domain.Career;
import com.example.TheFit.career.dto.CareerDto;
import com.example.TheFit.career.repository.CareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CareerService {
    private final CareerRepository careerRepository;
    @Autowired
    public CareerService(CareerRepository careerRepository) {
        this.careerRepository = careerRepository;
    }


    public void create(CareerDto careerDto) {
        Career career = Career.builder()
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

