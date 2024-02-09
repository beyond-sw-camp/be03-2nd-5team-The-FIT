package com.example.TheFit.member.dto;

import com.example.TheFit.member.domain.Gender;
import lombok.*;

@Data
public class MemberReqDto {
    private String name;
    private String password;
    private int cmHeight;
    private int kgWeight;
    private String profileImage;
    private String phoneNumber;
}
