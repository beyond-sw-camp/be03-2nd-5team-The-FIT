package com.example.TheFit.member.dto;

import com.example.TheFit.member.domain.Gender;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResDto {
    private Long id;
    private Long trainerId;
    private String name;
    private String email;
    private int cmHeight;
    private int kgWeight;
    private Gender gender;
    private String profileImage;
    private String phoneNumber;
}
