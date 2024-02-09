package com.example.TheFit.member.dto;

import com.example.TheFit.member.domain.Gender;
import com.example.TheFit.member.domain.Member;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResDto {
    private Long id;
    private String name;
    private String email;
    private int cmHeight;
    private int kgWeight;
    private Gender gender;
    private String profileImage;
    private String phoneNumber;
}
