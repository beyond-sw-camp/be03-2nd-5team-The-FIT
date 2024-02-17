package com.example.TheFit.trainer.dto;

import com.example.TheFit.trainer.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainerResDto {
    private Long id;
    private String name;
    private String email;
    private int cmHeight;
    private int kgWeight;
    private Gender gender;
    private String profileImage;
    private String phoneNumber;
}
