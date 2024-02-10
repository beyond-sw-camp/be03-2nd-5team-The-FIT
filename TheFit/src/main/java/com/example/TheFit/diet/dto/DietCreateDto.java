package com.example.TheFit.diet.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class DietCreateDto {
    private String imagePath;
    private String type;
    private String comment;
    private LocalDate dietDate;
}