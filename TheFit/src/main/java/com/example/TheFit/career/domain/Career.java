package com.example.TheFit.career.domain;

import com.example.TheFit.career.dto.CareerDto;
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
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String awards;
    private String license;
    private String work;

    public void update(CareerDto careerDto) {
        this.awards = careerDto.getAwards();
        this.license = careerDto.getLicense();
        this.work = careerDto.getWork();
    }
}
