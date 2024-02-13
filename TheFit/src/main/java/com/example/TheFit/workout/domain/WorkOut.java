package com.example.TheFit.workout.domain;

import com.example.TheFit.totalworkouts.domain.TotalWorkOuts;
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
    @OneToMany(mappedBy = "totalworkouts", cascade = CascadeType.ALL)
    private List<TotalWorkOuts> totalWorkOuts = new ArrayList<>();
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
}
