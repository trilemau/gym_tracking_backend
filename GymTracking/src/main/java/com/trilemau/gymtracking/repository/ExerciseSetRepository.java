package com.trilemau.gymtracking.repository;

import com.trilemau.gymtracking.domain.entity.ExerciseSet;
import com.trilemau.gymtracking.domain.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Long> {

    List<ExerciseSet> getAllByWorkout(Workout workout);
}
