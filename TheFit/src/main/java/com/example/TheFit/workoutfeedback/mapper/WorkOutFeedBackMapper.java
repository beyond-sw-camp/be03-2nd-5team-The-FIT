package com.example.TheFit.workoutfeedback.mapper;

import com.example.TheFit.workout.dto.WorkOutReqDto;
import com.example.TheFit.workout.dto.WorkOutResDto;
import com.example.TheFit.workoutfeedback.domain.WorkOutFeedBack;
import com.example.TheFit.workoutfeedback.dto.WorkOutFeedBackReqDto;
import com.example.TheFit.workoutfeedback.dto.WorkOutFeedBackResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WorkOutFeedBackMapper {
    WorkOutFeedBackMapper INSTANCE = Mappers.getMapper(WorkOutFeedBackMapper.class);
    @Mapping(source = "workOutListId", target = "workOutList.id")
    @Mapping(source = "trainerId", target = "trainer.id")
    WorkOutFeedBack toEntity(WorkOutFeedBackReqDto dto);

    @Mapping(source = "workOutList.id", target = "workOutListId")
    @Mapping(source = "trainer.id", target = "trainerId")
    WorkOutFeedBackResDto toDto(WorkOutFeedBack workOutFeedBack);

    void update(WorkOutFeedBackReqDto dto, @MappingTarget WorkOutFeedBack workOutFeedBack);
}
