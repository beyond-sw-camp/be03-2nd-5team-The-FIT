package com.example.TheFit.diet.service;

import com.example.TheFit.diet.domain.Diet;
import com.example.TheFit.diet.dto.DietDto;
import com.example.TheFit.diet.repository.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class DietService {

    private final DietRepository dietRepository;

    @Autowired
    public DietService(DietRepository dietRepository) {
        this.dietRepository = dietRepository;
    }


    public Diet create(DietDto dietdto) {
        Diet diet = Diet.builder()
                .imagePath(dietdto.getImagePath())
                .type(dietdto.getType())
                .comment(dietdto.getComment())
                .dietDate(dietdto.getDietDate())
                .build();
        return dietRepository.save(diet);
    }
    public DietDto findById(Long id) {
        Diet diet = dietRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found"));

        return DietDto.builder()
                .imagePath(diet.getImagePath())
                .type(diet.getType())
                .comment(diet.getComment())
                .dietDate(diet.getDietDate())
                .build();
    }
    @Transactional
    public Diet update(Long id, DietDto dietDto){
        Diet diet = dietRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("not found"));
        diet.update(dietDto);
        return dietRepository.save(diet);
    }
    public Diet delete(Long id) {
        Diet diet = dietRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("not found")));
        diet.delete();
        return diet;
    }
}
