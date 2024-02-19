package com.example.TheFit.workoutlist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkOutListResDto {
    private Long id;
    private Long memberId;
    private LocalDate workOutDate;
}