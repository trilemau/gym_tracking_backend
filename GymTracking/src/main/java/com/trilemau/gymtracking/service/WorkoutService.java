package com.trilemau.gymtracking.service;

import com.trilemau.gymtracking.domain.entity.ExerciseSet;
import com.trilemau.gymtracking.domain.entity.Workout;
import com.trilemau.gymtracking.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public Workout save(Workout workout) {
        if (workout == null) {
            throw new IllegalArgumentException("Workout is null");
        }

        return workoutRepository.save(workout);
    }

    public void delete(Workout workout) {
        if (workout == null) {
            throw new IllegalArgumentException("Workout is null");
        }

        workoutRepository.delete(workout);
    }

    public List<Workout> getAll() {
        return workoutRepository.findAll();
    }

    public Workout addWorkoutSet(ExerciseSet exerciseSet, Workout workout) {
        if (exerciseSet == null) {
            throw new IllegalArgumentException("Exercise set is null");
        }

        if (workout == null) {
            throw new IllegalArgumentException("Workout is null");
        }

        workout.addExerciseSet(exerciseSet);
        return workoutRepository.save(workout);
    }
}
