package com.example.TheFit.diet.domain;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagePath;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String comment;
    private LocalDate dietDate;
    @Builder.Default
    private String delYn="N";
    @CreationTimestamp
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime updatedTime;

    public void update(String imagePath, String type, String comment, LocalDate dietDate){
        this.imagePath = imagePath;
        this.type = type;
        this.comment = comment;
        this.dietDate = dietDate;
    }
    public void delete() {
        this.delYn = "Y";
    }

}
