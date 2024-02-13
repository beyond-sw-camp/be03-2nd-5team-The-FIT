package com.example.TheFit.diet.dto;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class DietDto {
    private String imagePath;
    private String type;
    private String comment;
    private LocalDate dietDate;
}
