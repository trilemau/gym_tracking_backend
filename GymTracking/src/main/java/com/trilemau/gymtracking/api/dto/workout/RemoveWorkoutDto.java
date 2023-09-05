package com.trilemau.gymtracking.api.dto.workout;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RemoveWorkoutDto {

    @NotNull
    Long workoutId;

    @NotNull
    Long userId;
}
