package com.example.TheFit.trainer.service;

import com.example.TheFit.member.domain.Gender;
import com.example.TheFit.trainer.domain.Trainer;
import com.example.TheFit.trainer.dto.TrainerCreateDto;
import com.example.TheFit.trainer.dto.TrainerReqDto;
import com.example.TheFit.trainer.dto.TrainerResDto;
import com.example.TheFit.trainer.repository.TrainerRepository;
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

    @Autowired
    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public void create(TrainerCreateDto trainerCreateDto) {
        Trainer trainer = Trainer.builder()
                .name(trainerCreateDto.getName())
                .email(trainerCreateDto.getEmail())
                .password(trainerCreateDto.getPassword())
                .cmHeight(trainerCreateDto.getCmHeight())
                .kgWeight(trainerCreateDto.getKgWeight())
                .phoneNumber(trainerCreateDto.getPhoneNumber())
                .build();
        trainerRepository.save(trainer);
    }

    public List<TrainerResDto> findAll() {
        List<Trainer> trainers = trainerRepository.findAll();
        return trainers.stream()
                .map(trainer -> TrainerResDto.builder()
                        .id(trainer.getId())
                        .name(trainer.getName())
                        .email(trainer.getEmail())
                        .cmHeight(trainer.getCmHeight())
                        .kgWeight(trainer.getKgWeight())
                        .profileImage(trainer.getProfileImage())
                        .phoneNumber(trainer.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());
    }

    public Trainer update(Long id, TrainerReqDto trainerReqDto) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("not found trainer"));
        trainer.update(
                trainerReqDto.getName(),
                trainerReqDto.getPassword(),
                trainerReqDto.getCmHeight(),
                trainerReqDto.getKgWeight(),
                trainerReqDto.getProfileImage(),
                trainerReqDto.getPhoneNumber());
        return trainerRepository.save(trainer);
    }

    @Transactional
    public void delete(Long id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found trainer"));
        trainerRepository.save(trainer);
    }
}