package com.example.TheFit.totalworkouts.domain;

import com.example.TheFit.diet.dto.DietDto;
import com.example.TheFit.totalworkouts.dto.TotalWorkOutsDto;
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
public class TotalWorkOuts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String target;

    public void update(TotalWorkOutsDto totalWorkOutsDto) {
        this.name = totalWorkOutsDto.getName();
        this.target = totalWorkOutsDto.getTarget();
    }
}
