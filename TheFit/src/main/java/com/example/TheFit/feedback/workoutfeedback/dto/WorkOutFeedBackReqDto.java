package com.example.TheFit.feedback.workoutfeedback.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkOutFeedBackReqDto {
    private Long workOutListId;
    private Long trainerId;
    private String feedBack;
    private String rating;
}