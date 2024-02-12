package com.example.TheFit.diet.dto;

import com.example.TheFit.diet.domain.Diet;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class DietResDto {
    private String imagePath;
    private String type;
    private String comment;
    private LocalDate dietDate;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
