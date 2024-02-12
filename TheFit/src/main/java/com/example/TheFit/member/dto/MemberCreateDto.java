package com.example.TheFit.member.dto;

import com.example.TheFit.member.domain.Gender;
import lombok.Data;

import javax.persistence.Column;

@Data
public class MemberCreateDto {
    private String name;
    private String email;
    private String password;
    private int cmHeight;
    private int kgWeight;
    private Gender gender;
    private String profileImage;
    private String phoneNumber;

}