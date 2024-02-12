package com.example.TheFit.trainer.dto;

import com.example.TheFit.trainer.domain.Gender;
import lombok.Data;

@Data
public class TrainerCreateDto {
    private String name;
    private String email;
    private String password;
    private int cmHeight;
    private int kgWeight;
    private Gender gender;
    private String profileImage;
    private String phoneNumber;
}
