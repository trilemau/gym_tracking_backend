package com.trilemau.gymtracking.service;

import com.trilemau.gymtracking.domain.entity.ExerciseSet;
import com.trilemau.gymtracking.domain.entity.Workout;
import com.trilemau.gymtracking.repository.ExerciseSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ExerciseSetService {

    private final ExerciseSetRepository exerciseSetRepository;

    public ExerciseSet save(ExerciseSet exerciseSet) {
        return exerciseSetRepository.save(exerciseSet);
    }

    public Optional<ExerciseSet> getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }

        return exerciseSetRepository.findById(id);
    }

    public List<ExerciseSet> getWorkoutExerciseSets(Workout workout) {
        return exerciseSetRepository.getAllByWorkout(workout);
    }
}
