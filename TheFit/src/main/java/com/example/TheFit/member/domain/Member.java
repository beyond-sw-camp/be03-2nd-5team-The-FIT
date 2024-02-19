package com.example.TheFit.member.domain;

import com.example.TheFit.member.dto.MemberReqDto;
import com.example.TheFit.trainer.domain.Trainer;
import com.example.TheFit.workoutlist.domain.WorkOutList;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private int cmHeight;
    private int kgWeight;
    @Enumerated
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
}
