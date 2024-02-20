package com.example.TheFit.workoutlist.repository;

import com.example.TheFit.workoutlist.domain.WorkOutList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOutListRepository extends JpaRepository<WorkOutList, Long> {
}
