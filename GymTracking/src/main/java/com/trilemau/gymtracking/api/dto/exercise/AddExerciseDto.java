package com.trilemau.gymtracking.api.dto.exercise;

import com.trilemau.gymtracking.domain.enums.MuscleGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class AddExerciseDto {

    @NotBlank
    private String name;

    @NotNull
    private Set<MuscleGroup> muscleGroups;

    private byte[] image;
}
