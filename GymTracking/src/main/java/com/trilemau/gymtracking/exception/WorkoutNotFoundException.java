package com.trilemau.gymtracking.exception;

import static java.lang.String.format;

public class WorkoutNotFoundException extends RuntimeException {
    public WorkoutNotFoundException(Long id) {
        super(format("Workout not found [id=%d]", id));
    }
}
