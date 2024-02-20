package com.example.TheFit.user.trainer.service;

import com.example.TheFit.user.dto.UserIdPassword;
import com.example.TheFit.user.entity.Role;
import com.example.TheFit.user.mapper.UserMapper;
import com.example.TheFit.user.repo.UserRepository;
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
    private final UserRepository userRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository, UserRepository userRepository) {
        this.trainerRepository = trainerRepository;
        this.userRepository = userRepository;
    }

    public Trainer create(TrainerReqDto trainerReqDto) {
        if(userRepository.findByEmail(trainerReqDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이메일이 중복입니다.");
        }
        Trainer trainer = userMapper.toEntity(trainerReqDto);
        userRepository.save(new UserIdPassword(trainer.email,trainer.password, Role.TRAINER));
        return trainerRepository.save(trainer);
    }

    public List<TrainerResDto> findAll() {
        return trainerRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public Trainer update(Long id, TrainerReqDto trainerReqDto) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found"));
        userMapper.update(trainerReqDto, trainer);
        return trainerRepository.save(trainer);
    }

    public void delete(Long id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trainer not found"));
        trainer.delete();
    }
}