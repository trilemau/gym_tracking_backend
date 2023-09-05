package com.trilemau.gymtracking.repository;

import com.trilemau.gymtracking.domain.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

}
