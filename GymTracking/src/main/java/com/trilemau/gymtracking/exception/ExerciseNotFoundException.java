package com.trilemau.gymtracking.exception;

import static java.lang.String.format;

public class ExerciseNotFoundException extends RuntimeException {

    public ExerciseNotFoundException(Long id) {
        super(format("Exercise not found [id=%d]", id));
    }
}
