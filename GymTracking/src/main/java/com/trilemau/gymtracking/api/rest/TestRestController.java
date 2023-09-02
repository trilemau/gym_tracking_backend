package com.trilemau.gymtracking.api.rest;

import com.trilemau.gymtracking.model.Exercise;
import com.trilemau.gymtracking.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
public class TestRestController {

    private final ExerciseService exerciseService;

    @GetMapping(path = "/all", produces = APPLICATION_JSON_VALUE)
    Collection<Exercise> getAllExercises() {
        return exerciseService.getAll();
    }
}
