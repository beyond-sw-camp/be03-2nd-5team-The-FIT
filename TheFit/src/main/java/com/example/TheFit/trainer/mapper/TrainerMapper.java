package com.example.TheFit.trainer.mapper;

import com.example.TheFit.trainer.domain.Trainer;
import com.example.TheFit.trainer.dto.TrainerReqDto;
import com.example.TheFit.trainer.dto.TrainerResDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TrainerMapper {
    TrainerMapper INSTANCE = Mappers.getMapper(TrainerMapper.class);

    Trainer toEntity(TrainerReqDto dto);
    TrainerResDto toDto(Trainer entity);
    void update(TrainerReqDto trainerReqDto, @MappingTarget Trainer trainer);
}