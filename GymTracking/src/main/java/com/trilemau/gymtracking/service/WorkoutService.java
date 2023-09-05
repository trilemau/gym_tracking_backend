package com.trilemau.gymtracking.service;

import com.trilemau.gymtracking.domain.entity.ExerciseSet;
import com.trilemau.gymtracking.domain.entity.User;
import com.trilemau.gymtracking.domain.entity.Workout;
import com.trilemau.gymtracking.exception.ExerciseSetNotInWorkoutException;
import com.trilemau.gymtracking.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public Workout save(Workout workout) {
        return workoutRepository.save(workout);
    }

    public Optional<Workout> getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }

        return workoutRepository.findById(id);
    }

    public List<Workout> getWorkoutsByUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null");
        }

        return workoutRepository.getAllByUser(user);
    }

    public void removeWorkoutSet(ExerciseSet exerciseSet, Workout workout) {
        if (exerciseSet == null) {
            throw new IllegalArgumentException("Exercise set is null");
        }

        if (workout == null) {
            throw new IllegalArgumentException("Workout is null");
        }

        if (workout.getExerciseSets().contains(exerciseSet) == false) {
            throw new ExerciseSetNotInWorkoutException(exerciseSet.getId(), workout.getId());
        }

        workout.removeExerciseSet(exerciseSet);
        workout = workoutRepository.save(workout);
        workoutRepository.flush(); // apparently a known bug ?
    }

    public void addWorkoutSet(ExerciseSet exerciseSet, Workout workout) {
        if (exerciseSet == null) {
            throw new IllegalArgumentException("Exercise set is null");
        }

        if (workout == null) {
            throw new IllegalArgumentException("Workout is null");
        }

        workout.addExerciseSet(exerciseSet);
        workout = workoutRepository.save(workout);
    }
}
