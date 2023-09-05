package com.trilemau.gymtracking.api.dto.workout;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
public class RemoveWorkoutDto {

    @NotNull
    private Long workoutId;

    @NotNull
    private Long userId;
}
