package com.example.TheFit.totalworkouts.service;

import com.example.TheFit.totalworkouts.domain.TotalWorkOuts;
import com.example.TheFit.totalworkouts.dto.TotalWorkOutsReqDto;
import com.example.TheFit.totalworkouts.dto.TotalWorkOutsResDto;
import com.example.TheFit.totalworkouts.mapper.TotalWorkOutsMapper;
import com.example.TheFit.totalworkouts.repository.TotalWorkOutsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TotalWorkOutsService {
    private final TotalWorkOutsRepository totalWorkOutsRepository;
    private final TotalWorkOutsMapper totalWorkOutsMapper = TotalWorkOutsMapper.INSTANCE;

    @Autowired
    public TotalWorkOutsService(TotalWorkOutsRepository totalWorkOutsRepository) {
        this.totalWorkOutsRepository = totalWorkOutsRepository;
    }

    public void create(TotalWorkOutsReqDto totalWorkOutsReqDto) {
        TotalWorkOuts totalWorkOuts = TotalWorkOutsMapper.INSTANCE.toEntity(totalWorkOutsReqDto);
        totalWorkOutsRepository.save(totalWorkOuts);
    }

    public List<TotalWorkOutsResDto> findAll() {
        List<TotalWorkOuts> totalWorkOuts = totalWorkOutsRepository.findAll();
        return totalWorkOuts.stream()
                .map(totalWorkOutsMapper::toDto)
                .collect(Collectors.toList());
    }
    public void update(Long id, TotalWorkOutsReqDto totalWorkOutsReqDto) {
        TotalWorkOuts totalWorkOuts = totalWorkOutsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TotalWorkOuts not found"));
        totalWorkOuts.update(totalWorkOutsReqDto);
    }
    public void delete(Long id) {
        totalWorkOutsRepository.deleteById(id);
    }
}