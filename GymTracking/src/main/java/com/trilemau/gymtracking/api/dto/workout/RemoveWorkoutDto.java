package com.trilemau.gymtracking.api.dto.workout;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RemoveWorkoutDto {

    @NotNull
    private Long workoutId;

    @NotNull
    private Long userId;
}
