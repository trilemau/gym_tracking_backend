package com.trilemau.gymtracking.api.dto.workout;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class UpdateWorkoutDto {

    @NotNull
    Long id;

    @NotNull
    LocalDate date;

    @NotNull
    String notes;
}
