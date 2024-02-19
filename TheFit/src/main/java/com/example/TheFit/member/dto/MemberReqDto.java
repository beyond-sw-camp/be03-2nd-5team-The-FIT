package com.example.TheFit.member.dto;

import com.example.TheFit.member.domain.Gender;
import com.example.TheFit.trainer.domain.Trainer;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberReqDto {
    private Long trainerId;
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
