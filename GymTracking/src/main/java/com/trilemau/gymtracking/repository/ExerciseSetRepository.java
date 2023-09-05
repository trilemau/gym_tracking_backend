package com.trilemau.gymtracking.repository;

import com.trilemau.gymtracking.domain.entity.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Long> {

}
