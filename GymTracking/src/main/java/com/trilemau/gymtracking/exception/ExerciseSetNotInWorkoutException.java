package com.trilemau.gymtracking.exception;

import static java.lang.String.format;

public class ExerciseSetNotInWorkoutException extends RuntimeException {

    public ExerciseSetNotInWorkoutException(Long exerciseSetId, Long workoutId) {
        super(format("ExerciseSet not found in Workout [exerciseSetId=%d, workoutId=%d]", exerciseSetId, workoutId));
    }
}
