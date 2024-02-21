package com.example.TheFit.feedback.dietfeedback.domain;

import com.example.TheFit.feedback.FeedBack;
import com.example.TheFit.feedback.dietfeedback.dto.DietFeedBackReqDto;
import com.example.TheFit.diet.domain.Diet;
import com.example.TheFit.user.trainer.domain.Trainer;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class DietFeedBack extends FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "diet_id")
    private Diet diet;

    public void update(Diet diet, Trainer trainer,DietFeedBackReqDto dietFeedBackReqDto) {
        this.trainer = trainer;
        this.diet = diet;
        this.feedBack = dietFeedBackReqDto.getFeedBack();
        this.rating = dietFeedBackReqDto.getRating();
    }

}
