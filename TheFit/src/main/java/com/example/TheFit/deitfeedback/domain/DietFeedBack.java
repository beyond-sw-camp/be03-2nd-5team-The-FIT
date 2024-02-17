package com.example.TheFit.deitfeedback.domain;

import com.example.TheFit.deitfeedback.dto.DietFeedBackReqDto;
import com.example.TheFit.diet.domain.Diet;
import com.example.TheFit.trainer.domain.Trainer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DietFeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "diet_id")
    private Diet diet;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
    private String feedBack;
    @Column(nullable = false)
    private String rating;

    public void update(DietFeedBackReqDto dietFeedBackReqDto) {
        this.feedBack = dietFeedBackReqDto.getFeedBack();
        this.rating = dietFeedBackReqDto.getRating();
    }

}
