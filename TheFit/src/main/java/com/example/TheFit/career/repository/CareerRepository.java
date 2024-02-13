package com.example.TheFit.career.repository;

import com.example.TheFit.career.domain.Career;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepository extends JpaRepository<Career, Long> {
}
