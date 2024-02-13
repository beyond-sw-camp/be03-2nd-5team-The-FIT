package com.example.TheFit.totalworkouts.service;


import com.example.TheFit.member.domain.Member;
import com.example.TheFit.totalworkouts.domain.TotalWorkOuts;
import com.example.TheFit.totalworkouts.dto.TotalWorkOutsDto;
import com.example.TheFit.totalworkouts.repository.TotalWorkOutsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TotalWorkOutsService {
    private final TotalWorkOutsRepository totalWorkOutsRepository;

    @Autowired
    public TotalWorkOutsService(TotalWorkOutsRepository totalWorkOutsRepository) {
        this.totalWorkOutsRepository = totalWorkOutsRepository;
    }

    public void create(TotalWorkOutsDto totalWorkOutsCreateDto) {
        TotalWorkOuts totalWorkOuts = TotalWorkOuts.builder()
                .name(totalWorkOutsCreateDto.getName())
                .target(totalWorkOutsCreateDto.getTarget())
                .build();
        totalWorkOutsRepository.save(totalWorkOuts);
    }

    public List<TotalWorkOutsDto> findAll() {
        List<TotalWorkOuts> totalWorkOutsList = totalWorkOutsRepository.findAll();
        List<TotalWorkOutsDto> totalWorkOutsDtos = new ArrayList<>();
        for (TotalWorkOuts totalWorkOuts : totalWorkOutsList) {
            TotalWorkOutsDto totalWorkOutsDto = TotalWorkOutsDto.builder()
                    .name(totalWorkOuts.getName())
                    .target(totalWorkOuts.getTarget())
                    .build();
            totalWorkOutsDtos.add(totalWorkOutsDto);
        }
        return totalWorkOutsDtos;
    }

    public TotalWorkOuts update(Long id, TotalWorkOutsDto totalWorkOutsDto) {
        TotalWorkOuts totalWorkOutsUpdate = totalWorkOutsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found"));
        totalWorkOutsUpdate.update(totalWorkOutsDto);
        return totalWorkOutsRepository.save(totalWorkOutsUpdate);
    }

    public void delete(Long id) {
        totalWorkOutsRepository.deleteById(id);
    }
}

