package com.example.TheFit.workoutfeedback.domain;

import com.example.TheFit.trainer.domain.Trainer;
import com.example.TheFit.workoutfeedback.dto.WorkOutFeedBackReqDto;
import com.example.TheFit.workoutlist.domain.WorkOutList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkOutFeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "workOutList_id")
    private WorkOutList workOutList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
    private String feedBack;
    private String rating;

    public void update(WorkOutFeedBackReqDto workOutFeedBackReqDto) {
        this.feedBack = workOutFeedBackReqDto.getFeedBack();
        this.rating = workOutFeedBackReqDto.getRating();
    }
}
