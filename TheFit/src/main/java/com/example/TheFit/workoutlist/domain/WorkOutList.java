package com.example.TheFit.workoutlist.domain;

import com.example.TheFit.member.domain.Member;
import com.example.TheFit.workout.domain.WorkOut;
import com.example.TheFit.workoutlist.dto.WorkOutListReqDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkOutList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToMany(mappedBy = "workOutList", cascade = CascadeType.REMOVE)
    private List<WorkOut> workOuts;
    private LocalDate workOutDate;

}

