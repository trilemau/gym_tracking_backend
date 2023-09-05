package com.trilemau.gymtracking.exception;

import static java.lang.String.format;

public class ExerciseSetNotFoundException extends RuntimeException {

    public ExerciseSetNotFoundException(Long id) {
        super(format("Exercise set not found [id=%d]", id));
    }
}

