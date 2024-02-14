package com.example.TheFit.totalworkouts.domain;

import com.example.TheFit.totalworkouts.dto.TotalWorkOutsDto;
import com.example.TheFit.workout.domain.WorkOut;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TotalWorkOuts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String target;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "workOut")
//    private WorkOut workOut;

    public void update(TotalWorkOutsDto totalWorkOutsDto) {
        this.name = totalWorkOutsDto.getName();
        this.target = totalWorkOutsDto.getTarget();
    }
}
