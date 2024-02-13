package com.example.TheFit.career.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CareerDto {
    private String awards;
    private String license;
    private String work;
}
