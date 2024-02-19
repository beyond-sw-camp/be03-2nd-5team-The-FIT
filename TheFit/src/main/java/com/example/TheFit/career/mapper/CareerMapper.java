package com.example.TheFit.career.mapper;

import com.example.TheFit.career.domain.Career;
import com.example.TheFit.career.dto.CareerReqDto;
import com.example.TheFit.career.dto.CareerResDto;
import com.example.TheFit.trainer.domain.Trainer;
import com.example.TheFit.trainer.repository.TrainerRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CareerMapper {
    CareerMapper INSTANCE = Mappers.getMapper(CareerMapper.class);

    Career toEntity(CareerReqDto careerReqDto);
    @Mapping(source = "trainer.id", target = "trainerId")
    CareerResDto toDto(Career career);
    void update(CareerReqDto careerReqDto, @MappingTarget Career career);

}