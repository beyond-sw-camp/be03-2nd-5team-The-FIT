package com.example.TheFit.deitfeedback.repository;

import com.example.TheFit.deitfeedback.domain.DietFeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietFeedBackRepository extends JpaRepository<DietFeedBack, Long> {
}