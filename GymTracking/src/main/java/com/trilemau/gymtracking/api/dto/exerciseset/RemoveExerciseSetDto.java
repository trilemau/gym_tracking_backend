package com.trilemau.gymtracking.api.dto.exerciseset;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RemoveExerciseSetDto {

    @NotNull
    private Long exerciseSetId;

    @NotNull
    private Long workoutId;
}
