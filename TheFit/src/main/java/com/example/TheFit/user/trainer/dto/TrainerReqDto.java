package com.example.TheFit.user.trainer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainerReqDto {
    private String name;
    private String email;
    private String password;
    private int cmHeight;
    private int kgWeight;
    private String gender;
    private String role;
    private String profileImage;
    private String phoneNumber;
}
