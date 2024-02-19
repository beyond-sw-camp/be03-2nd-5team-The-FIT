package com.example.TheFit.trainer.domain;

import com.example.TheFit.trainer.dto.TrainerReqDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private int cmHeight;
    private int kgWeight;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    private String profileImage;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @Builder.Default
    private String delYn="N";
    @CreationTimestamp
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime updatedTime;

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
