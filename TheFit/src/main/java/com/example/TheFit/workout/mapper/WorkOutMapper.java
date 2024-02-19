package com.example.TheFit.workout.mapper;

import com.example.TheFit.workout.domain.WorkOut;
import com.example.TheFit.workout.dto.WorkOutReqDto;
import com.example.TheFit.workout.dto.WorkOutResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WorkOutMapper {
    WorkOutMapper INSTANCE = Mappers.getMapper(WorkOutMapper.class);

    @Mapping(source = "totalWorkOutsId", target = "totalWorkOuts.id")
    @Mapping(source = "workOutListId", target = "workOutList.id")
    WorkOut toEntity(WorkOutReqDto dto);
    @Mapping(source = "totalWorkOuts.id", target = "totalWorkOutsId")
    @Mapping(source = "workOutList.id", target = "workOutListId")
    WorkOutResDto toDto(WorkOut workOut);

    void update(WorkOutReqDto dto, @MappingTarget WorkOut workOut);
}
