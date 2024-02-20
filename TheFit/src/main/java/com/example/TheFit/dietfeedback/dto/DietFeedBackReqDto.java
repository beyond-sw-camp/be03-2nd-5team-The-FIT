package com.example.TheFit.dietfeedback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DietFeedBackReqDto {
    private Long dietId;
    private Long trainerId;
    private String feedBack;
    private String rating;
}