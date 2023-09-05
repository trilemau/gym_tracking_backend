package com.trilemau.gymtracking.api.dto.exerciseset;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddExerciseSetDto {

    @NotNull
    private Long workoutId;

    @NotNull
    private Long exerciseId;

    // TODO check if @NotNull is needed
    private int reps;

    // TODO check if @NotNull is needed
    private double weight;
}
