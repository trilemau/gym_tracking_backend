package com.trilemau.gymtracking.repository;

import com.trilemau.gymtracking.domain.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    public List<Exercise> findAllByNameContains(String substring);
}
