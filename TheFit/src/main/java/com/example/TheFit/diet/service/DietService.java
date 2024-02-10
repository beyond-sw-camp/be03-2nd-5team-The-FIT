package com.example.TheFit.diet.service;

import com.example.TheFit.diet.domain.Diet;
import com.example.TheFit.diet.dto.DietCreateDto;
import com.example.TheFit.diet.dto.DietReqDto;
import com.example.TheFit.diet.dto.DietResDto;
import com.example.TheFit.diet.repository.DietRepository;
import com.example.TheFit.member.domain.Member;
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

    @Transactional
    public Diet create(DietCreateDto dietCreateDto) {
        Diet diet = Diet.builder()
                .imagePath(dietCreateDto.getImagePath())
                .type(dietCreateDto.getType())
                .comment(dietCreateDto.getComment())
                .dietDate(dietCreateDto.getDietDate())
                .build();
        return dietRepository.save(diet);
    }
    public DietResDto findById(Long id) {
        Diet diet = dietRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found"));

        return DietResDto.builder()
                .imagePath(diet.getImagePath())
                .type(diet.getType())
                .comment(diet.getComment())
                .dietDate(diet.getDietDate())
                .createdTime(diet.getCreatedTime())
                .updatedTime(diet.getUpdatedTime())
                .build();
    }
    public Diet update(Long id, DietReqDto dietReqDto){
        Diet diet = dietRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("not found"));
        diet.update(
                dietReqDto.getImagePath(),
                dietReqDto.getType(),
                dietReqDto.getComment(),
                dietReqDto.getDietDate());
        return dietRepository.save(diet);
    }
    public Diet delete(Long id) {
        Diet diet = dietRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("not found")));
        diet.delete();
        return diet;
    }
}
