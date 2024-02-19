package com.example.TheFit.user.trainer.service;

import com.example.TheFit.user.UserMapper;
import com.example.TheFit.user.trainer.domain.Trainer;
import com.example.TheFit.user.trainer.dto.TrainerReqDto;
import com.example.TheFit.user.trainer.dto.TrainerResDto;
import com.example.TheFit.user.trainer.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public void create(TrainerReqDto trainerReqDto) {
        Trainer trainer = userMapper.toEntity(trainerReqDto);
        trainerRepository.save(trainer);
    }

    public List<TrainerResDto> findAll() {
        return trainerRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public void update(Long id, TrainerReqDto trainerReqDto) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found"));
        System.out.println(trainer.getCmHeight());
        userMapper.update(trainerReqDto, trainer);
        System.out.println(trainer.getCmHeight());
        trainerRepository.save(trainer);
    }

    public void delete(Long id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found"));
        trainer.delete();
    }
}