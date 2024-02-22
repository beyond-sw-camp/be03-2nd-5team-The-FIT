package com.example.TheFit.feedback.workoutfeedback.repository;



import com.example.TheFit.feedback.workoutfeedback.domain.WorkOutFeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkOutFeedBackRepository extends JpaRepository<WorkOutFeedBack, Long> {
   Optional<WorkOutFeedBack> findByUploadDate(String date);
}