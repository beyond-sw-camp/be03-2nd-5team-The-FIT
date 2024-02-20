package com.example.TheFit.dietfeedback.repository;

import com.example.TheFit.dietfeedback.domain.DietFeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietFeedBackRepository extends JpaRepository<DietFeedBack, Long> {
}