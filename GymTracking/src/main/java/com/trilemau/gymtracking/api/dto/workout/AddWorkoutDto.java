package com.trilemau.gymtracking.api.dto.workout;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddWorkoutDto {

    @NotNull
    private Long userId;
}
