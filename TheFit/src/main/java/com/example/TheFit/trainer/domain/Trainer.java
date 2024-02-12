package com.example.TheFit.trainer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    @Column
    private Gender gender;
    private String profileImage;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @Builder.Default
    private String delYn="N";
    @CreationTimestamp
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime updatedTime;

    public void update(String name, String password, int cmHeight, int kgWeight, String profileImage, String phoneNumber) {
        this.name = name;
        this.password = password;
        this.cmHeight = cmHeight;
        this.kgWeight = kgWeight;
        this.profileImage = profileImage;
        this.phoneNumber = phoneNumber;
    }
    public void delete() {
        this.delYn = "Y";
    }
}
