package com.example.TheFit.career.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
public class CareerDto {
    private Long TrainerId;
    private String awards;
    private String license;
    private String work;
}
