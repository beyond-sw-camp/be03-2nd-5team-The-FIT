package com.example.TheFit.workoutfeedback.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkOutFeedBackResDto {
    private Long id;
    private Long workOutListId;
    private Long trainerId;
    private String feedBack;
    private String rating;
}