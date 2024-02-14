package com.example.TheFit.workout.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class WorkOutDto {
    private int sets;
    private int weight;
    private int reps;
    private String restTime;
    private int performance;
}
