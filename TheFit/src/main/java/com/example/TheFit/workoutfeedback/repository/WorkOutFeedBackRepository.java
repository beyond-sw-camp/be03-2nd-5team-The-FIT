package com.example.TheFit.workoutfeedback.repository;



import com.example.TheFit.workoutfeedback.domain.WorkOutFeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOutFeedBackRepository extends JpaRepository<WorkOutFeedBack, Long> {
}