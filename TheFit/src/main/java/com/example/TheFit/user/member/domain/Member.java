package com.example.TheFit.user.member.domain;

import com.example.TheFit.user.member.dto.MemberReqDto;
import com.example.TheFit.user.trainer.domain.Trainer;
import com.example.TheFit.user.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Member extends User {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    public void update(MemberReqDto memberReqDto) {
        this.name = memberReqDto.getName();
        this.password = memberReqDto.getPassword();
        this.cmHeight = memberReqDto.getCmHeight();
        this.kgWeight = memberReqDto.getKgWeight();
        this.profileImage = memberReqDto.getProfileImage();
        this.phoneNumber = memberReqDto.getPhoneNumber();
    }

    public void delete() {
        this.delYn = "Y";
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}