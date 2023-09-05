package com.trilemau.gymtracking.error.exception;

import static java.lang.String.format;

public class ExerciseSetNotInWorkoutException extends RuntimeException {

    public ExerciseSetNotInWorkoutException(Long exerciseSetId, Long workoutId) {
        super(format("ExerciseSet %d not found in Workout %d", exerciseSetId, workoutId));
    }
}
