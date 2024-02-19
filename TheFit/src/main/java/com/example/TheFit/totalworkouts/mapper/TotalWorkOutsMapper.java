package com.example.TheFit.totalworkouts.mapper;

import com.example.TheFit.totalworkouts.domain.TotalWorkOuts;
import com.example.TheFit.totalworkouts.dto.TotalWorkOutsReqDto;
import com.example.TheFit.totalworkouts.dto.TotalWorkOutsResDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import javax.swing.text.html.parser.Entity;

@Mapper(componentModel = "spring")
public interface TotalWorkOutsMapper {
    TotalWorkOutsMapper INSTANCE = Mappers.getMapper(TotalWorkOutsMapper.class);
    TotalWorkOuts toEntity(TotalWorkOutsReqDto dto);
    TotalWorkOutsResDto toDto(TotalWorkOuts totalWorkOuts);

    void update(TotalWorkOutsReqDto dto, @MappingTarget TotalWorkOuts totalWorkOuts);
}
