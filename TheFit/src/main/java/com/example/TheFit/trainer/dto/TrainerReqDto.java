package com.example.TheFit.trainer.dto;

import lombok.Data;

@Data
public class TrainerReqDto {
    private String name;
    private String password;
    private int cmHeight;
    private int kgWeight;
    private String profileImage;
    private String phoneNumber;
}
