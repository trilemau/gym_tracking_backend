package com.trilemau.gymtracking.api.dto.exercise;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RemoveExerciseDto {

    @NotNull
    private Long exerciseId;
}
