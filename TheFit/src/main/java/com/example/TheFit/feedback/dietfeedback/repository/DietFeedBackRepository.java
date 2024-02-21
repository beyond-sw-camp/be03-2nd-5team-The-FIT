package com.example.TheFit.feedback.dietfeedback.repository;

import com.example.TheFit.feedback.dietfeedback.domain.DietFeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietFeedBackRepository extends JpaRepository<DietFeedBack, Long> {
}