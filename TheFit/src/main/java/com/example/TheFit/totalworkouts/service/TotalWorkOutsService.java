package com.example.TheFit.totalworkouts.service;

import com.example.TheFit.totalworkouts.domain.TotalWorkOuts;
import com.example.TheFit.totalworkouts.dto.TotalWorkOutsReqDto;
import com.example.TheFit.totalworkouts.dto.TotalWorkOutsResDto;
import com.example.TheFit.totalworkouts.repository.TotalWorkOutsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TotalWorkOutsService {
    private final TotalWorkOutsRepository totalWorkOutsRepository;

    @Autowired
    public TotalWorkOutsService(TotalWorkOutsRepository totalWorkOutsRepository) {
        this.totalWorkOutsRepository = totalWorkOutsRepository;
    }

    public void create(TotalWorkOutsReqDto totalWorkOutsReqDto) {
        TotalWorkOuts totalWorkOuts = TotalWorkOuts.builder()
                .name(totalWorkOutsReqDto.getName())
                .target(totalWorkOutsReqDto.getTarget())
                .build();
        totalWorkOutsRepository.save(totalWorkOuts);
    }

    public List<TotalWorkOutsResDto> findAll() {
        return totalWorkOutsRepository.findAll().stream()
                .map(totalWorkOuts -> new TotalWorkOutsResDto(totalWorkOuts.getId(), totalWorkOuts.getName(), totalWorkOuts.getTarget()))
                .collect(Collectors.toList());
    }

    public void update(Long id, TotalWorkOutsReqDto totalWorkOutsReqDto) {
        TotalWorkOuts totalWorkOutsUpdate = totalWorkOutsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TotalWorkOuts not found"));
        totalWorkOutsUpdate.update(new TotalWorkOutsReqDto(totalWorkOutsReqDto.getName(), totalWorkOutsReqDto.getTarget())); // Update the method to accept TotalWorkOutsReqDto
        totalWorkOutsRepository.save(totalWorkOutsUpdate);
    }

    public void delete(Long id) {
        totalWorkOutsRepository.deleteById(id);
    }
}