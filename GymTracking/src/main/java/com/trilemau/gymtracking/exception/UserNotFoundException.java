package com.trilemau.gymtracking.exception;

import static java.lang.String.format;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super(format("Workout not found [id=%d]", id));
    }
}
