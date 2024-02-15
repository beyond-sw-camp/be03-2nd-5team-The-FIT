package com.example.TheFit.workoutlist.dto;

import com.example.TheFit.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class WorkOutListDto {
    private Long MemberId;
    private LocalDate workOutDate;
}
