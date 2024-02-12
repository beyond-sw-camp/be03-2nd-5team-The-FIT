package com.example.TheFit.totalworkouts.service;


import com.example.TheFit.totalworkouts.domain.TotalWorkOuts;
import com.example.TheFit.totalworkouts.dto.TotalWorkOutsCreateDto;
import com.example.TheFit.totalworkouts.dto.TotalWorkOutsResDto;
import com.example.TheFit.totalworkouts.repository.TotalWorkOutsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TotalWorkOutsService {
    private final TotalWorkOutsRepository totalWorkOutsRepository;

    @Autowired
    public TotalWorkOutsService(TotalWorkOutsRepository totalWorkOutsRepository) {
        this.totalWorkOutsRepository = totalWorkOutsRepository;
    }

    public void create(TotalWorkOutsCreateDto totalWorkOutsCreateDto) {
        TotalWorkOuts totalWorkOuts = TotalWorkOuts.builder()
                .name(totalWorkOutsCreateDto.getName())
                .target(totalWorkOutsCreateDto.getTarget())
                .build();
        totalWorkOutsRepository.save(totalWorkOuts);
    }
    public List<TotalWorkOutsResDto> findAll() {
        List<TotalWorkOuts> totalWorkOutsList = totalWorkOutsRepository.findAll();
        List<TotalWorkOutsResDto> totalWorkOutsResDtos = new ArrayList<>();
        for (TotalWorkOuts totalWorkOuts : totalWorkOutsList) {
            TotalWorkOutsResDto totalWorkOutsResDto = TotalWorkOutsResDto.builder()
                    .name(totalWorkOuts.getName())
                    .target(totalWorkOuts.getTarget())
                    .build();
            totalWorkOutsResDtos.add(totalWorkOutsResDto);
        }
        return totalWorkOutsResDtos;
    }

}
