package com.trilemau.gymtracking.error.exception;

import static java.lang.String.format;

public class WorkoutNotInUserException extends RuntimeException {

    public WorkoutNotInUserException(Long workoutId, Long userId) {
        super(format("Workout %d not found in User %d", workoutId, userId));
    }
}
