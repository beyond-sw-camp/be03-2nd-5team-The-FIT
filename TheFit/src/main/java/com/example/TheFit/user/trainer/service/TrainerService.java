package com.example.TheFit.user.trainer.service;

import com.example.TheFit.common.ErrorCode;
import com.example.TheFit.common.TheFitBizException;
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

    public Trainer create(TrainerReqDto trainerReqDto) throws TheFitBizException {
        if(userRepository.findByEmail(trainerReqDto.getEmail()).isPresent()){
            throw new TheFitBizException(ErrorCode.ID_DUPLICATE);
        }
        Trainer trainer = userMapper.toEntity(trainerReqDto);
        userRepository.save(new UserIdPassword(trainer.email,trainer.password,trainer.name,Role.TRAINER));
        return trainerRepository.save(trainer);
    }

    public List<TrainerResDto> findAll() {
        return trainerRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public Trainer update(Long id, TrainerReqDto trainerReqDto) throws TheFitBizException{
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new TheFitBizException(ErrorCode.NOT_FOUND_TRAINER));
        userMapper.update(trainerReqDto, trainer);
        return trainerRepository.save(trainer);
    }

    public void delete(Long id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new TheFitBizException(ErrorCode.NOT_FOUND_TRAINER));
        trainer.delete();
    }
}