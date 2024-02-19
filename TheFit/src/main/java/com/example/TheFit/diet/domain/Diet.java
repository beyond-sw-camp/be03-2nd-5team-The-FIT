package com.example.TheFit.diet.domain;

import com.example.TheFit.diet.dto.DietReqDto;
import com.example.TheFit.user.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String imagePath;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String comment;
    private LocalDate dietDate;
    @CreationTimestamp
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime updatedTime;

    public void update(DietReqDto dietReqDto){
        this.imagePath = dietReqDto.getImagePath();
        this.type = dietReqDto.getType();
        this.comment = dietReqDto.getComment();
        this.dietDate = dietReqDto.getDietDate();
    }
}
