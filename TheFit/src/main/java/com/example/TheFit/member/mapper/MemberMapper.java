package com.example.TheFit.member.mapper;

import com.example.TheFit.member.domain.Member;
import com.example.TheFit.member.dto.MemberReqDto;
import com.example.TheFit.member.dto.MemberResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    Member toEntity(MemberReqDto dto);

    @Mapping(source = "trainer.id", target = "trainerId")
    MemberResDto toDto(Member member);

    void update(MemberReqDto dto, @MappingTarget Member member);

}