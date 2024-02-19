package com.example.TheFit.deitfeedback.mapper;

import com.example.TheFit.deitfeedback.domain.DietFeedBack;
import com.example.TheFit.deitfeedback.dto.DietFeedBackReqDto;
import com.example.TheFit.deitfeedback.dto.DietFeedBackResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DietFeedBackMapper {
    DietFeedBackMapper INSTANCE = Mappers.getMapper(DietFeedBackMapper.class);

    @Mapping(source = "dietId", target = "diet.id")
    @Mapping(source = "trainerId", target = "trainer.id")
    DietFeedBack toEntity(DietFeedBackReqDto dto);

    @Mapping(source = "diet.id", target = "dietId")
    @Mapping(source = "trainer.id", target = "trainerId")
    DietFeedBackResDto toDto(DietFeedBack dietFeedBack);

    void update(DietFeedBackReqDto dietFeedBackReqDto, @MappingTarget DietFeedBack dietFeedBack);
}