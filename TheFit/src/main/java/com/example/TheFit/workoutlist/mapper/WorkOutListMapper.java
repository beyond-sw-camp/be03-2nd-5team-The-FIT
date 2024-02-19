package com.example.TheFit.workoutlist.mapper;

import com.example.TheFit.diet.domain.Diet;
import com.example.TheFit.diet.dto.DietReqDto;
import com.example.TheFit.user.member.domain.Member;
import com.example.TheFit.workoutfeedback.domain.WorkOutFeedBack;
import com.example.TheFit.workoutfeedback.dto.WorkOutFeedBackReqDto;
import com.example.TheFit.workoutfeedback.dto.WorkOutFeedBackResDto;
import com.example.TheFit.workoutlist.domain.WorkOutList;
import com.example.TheFit.workoutlist.dto.WorkOutListReqDto;
import com.example.TheFit.workoutlist.dto.WorkOutListResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface WorkOutListMapper {
    WorkOutListMapper INSTANCE = Mappers.getMapper(WorkOutListMapper.class);

    default WorkOutList toEntity(Member member,WorkOutListReqDto dto){
        if ( dto == null ) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse(dto.getWorkOutDate(), formatter);
        return WorkOutList.builder()
                .member(member)
                .workOutDate(date)
                .build();
    }

    default WorkOutListResDto toDto(WorkOutList workOutList){
        if ( workOutList == null ) {
            return null;
        }
        return WorkOutListResDto.builder()
                .id(workOutList.getId())
                .memberId(workOutList.getId())
                .workOutDate(workOutList.getWorkOutDate())
                .build();
    }
}
