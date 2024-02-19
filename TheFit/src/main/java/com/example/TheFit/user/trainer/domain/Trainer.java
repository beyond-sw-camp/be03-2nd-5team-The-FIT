package com.example.TheFit.user.trainer.domain;

import com.example.TheFit.user.User;
import com.example.TheFit.user.trainer.dto.TrainerReqDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import javax.persistence.*;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
public class Trainer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public void update(TrainerReqDto trainerReqDto) {
        this.name = trainerReqDto.getName();
        this.password = trainerReqDto.getPassword();
        this.cmHeight = trainerReqDto.getCmHeight();
        this.kgWeight = trainerReqDto.getKgWeight();
        this.profileImage = trainerReqDto.getProfileImage();
        this.phoneNumber = trainerReqDto.getPhoneNumber();
    }
    public void delete() {
        this.delYn = "Y";
    }
}
