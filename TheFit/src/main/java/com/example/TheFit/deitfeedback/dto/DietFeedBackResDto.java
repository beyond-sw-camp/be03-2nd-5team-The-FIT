package com.example.TheFit.deitfeedback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DietFeedBackResDto {
    private Long id;
    private Long dietId;
    private Long trainerId;
    private String feedBack;
    private String rating;
}