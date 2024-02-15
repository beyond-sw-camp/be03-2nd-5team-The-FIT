package com.example.TheFit.workout.domain;

import com.example.TheFit.career.dto.CareerDto;
import com.example.TheFit.totalworkouts.domain.TotalWorkOuts;
import com.example.TheFit.workout.dto.WorkOutDto;
import com.example.TheFit.workoutlist.domain.WorkOutList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TotalWorkOuts_id")
    private TotalWorkOuts totalWorkOuts;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WorkOutList_id")
    private WorkOutList workOutList;
    @Column(nullable = false)
    private int sets;
    @Column(nullable = false)
    private int weight;
    @Column(nullable = false)
    private int reps;
    private String restTime;
    @Column(nullable = false)
    private int performance;
    @CreationTimestamp
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime updatedTime;

    public void update(WorkOutDto workOutDto) {
        this.sets = workOutDto.getSets();
        this.weight = workOutDto.getWeight();
        this.reps = workOutDto.getReps();
        this.restTime = workOutDto.getRestTime();
        this.performance = workOutDto.getPerformance();

    }
}
