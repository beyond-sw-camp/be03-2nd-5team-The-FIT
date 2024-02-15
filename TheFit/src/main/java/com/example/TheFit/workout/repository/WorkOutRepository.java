package com.example.TheFit.workout.repository;

import com.example.TheFit.career.domain.Career;
import com.example.TheFit.workout.domain.WorkOut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkOutRepository extends JpaRepository<WorkOut, Long> {
}
