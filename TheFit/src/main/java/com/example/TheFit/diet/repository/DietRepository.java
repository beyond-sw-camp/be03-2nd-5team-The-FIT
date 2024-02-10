package com.example.TheFit.diet.repository;

import com.example.TheFit.diet.domain.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietRepository extends JpaRepository<Diet, Long> {
}