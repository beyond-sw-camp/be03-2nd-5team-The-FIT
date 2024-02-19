package com.example.TheFit.user;

import com.example.TheFit.user.member.domain.Member;
import com.example.TheFit.user.member.dto.MemberReqDto;
import com.example.TheFit.user.member.dto.MemberResDto;
import com.example.TheFit.user.trainer.domain.Trainer;
import com.example.TheFit.user.trainer.dto.TrainerReqDto;
import com.example.TheFit.user.trainer.dto.TrainerResDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Member toEntity(MemberReqDto dto);

    Trainer toEntity(TrainerReqDto dto);

    MemberResDto toDto(Member member);

    TrainerResDto toDto(Trainer trainer);

    void update(MemberReqDto dto, @MappingTarget Member member);

    void update(TrainerReqDto dto, @MappingTarget Trainer trainer);

}