package com.trilemau.gymtracking.api.dto.workout;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateWorkoutDto {

    @NotNull
    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private String notes;
}
