package com.example.TheFit.user.member.dto;

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
