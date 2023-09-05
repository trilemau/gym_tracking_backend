package com.trilemau.gymtracking.exception;

import static java.lang.String.format;

public class WorkoutNotInUserException extends RuntimeException {

    public WorkoutNotInUserException(Long workoutId, Long userId) {
        super(format("Workout not found in User [workoutId=%d, userId=%d]", workoutId, userId));
    }
}
