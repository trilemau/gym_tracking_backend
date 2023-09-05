package com.trilemau.gymtracking.service;

import com.trilemau.gymtracking.domain.entity.ExerciseSet;
import com.trilemau.gymtracking.repository.ExerciseSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class ExerciseSetService {

    private final ExerciseSetRepository exerciseSetRepository;

    public ExerciseSet save(ExerciseSet exerciseSet) {
        return exerciseSetRepository.save(exerciseSet);
    }

    public void delete(ExerciseSet exerciseSet) {
        exerciseSetRepository.delete(exerciseSet);
    }
}
