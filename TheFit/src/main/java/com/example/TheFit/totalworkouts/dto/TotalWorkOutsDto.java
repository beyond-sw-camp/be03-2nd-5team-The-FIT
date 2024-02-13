package com.example.TheFit.totalworkouts.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TotalWorkOutsDto {
    private String name;
    private String target;
}
