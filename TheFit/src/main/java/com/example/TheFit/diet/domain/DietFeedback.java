package com.example.TheFit.diet.domain;

import com.example.TheFit.trainer.domain.Trainer;
import com.example.TheFit.workoutlist.domain.WorkOutList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
public class DietFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diet_id")
    private Diet diet;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
    private String feedback;
    @Column(nullable = false)
    private String rating;

}
