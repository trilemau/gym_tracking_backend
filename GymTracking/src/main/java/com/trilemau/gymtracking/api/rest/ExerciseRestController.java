package com.trilemau.gymtracking.api.rest;

import com.trilemau.gymtracking.api.dto.exercise.AddExerciseDto;
import com.trilemau.gymtracking.api.dto.exercise.RemoveExerciseDto;
import com.trilemau.gymtracking.api.dto.exercise.UpdateExerciseDto;
import com.trilemau.gymtracking.domain.entity.Exercise;
import com.trilemau.gymtracking.exception.ExerciseNotFoundException;
import com.trilemau.gymtracking.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ExerciseRestController {

    private static final String ADD_EXERCISE_PATH = "/v1/exercise/add";
    private static final String UPDATE_EXERCISE_PATH = "/v1/exercise/update";
    private static final String REMOVE_EXERCISE_PATH = "/v1/exercise/remove";
    private static final String GET_WORKOUT_EXERCISES_PATH = "/v1/exercise/get-exercises";

    private ExerciseService exerciseService;

    @PostMapping(path = ADD_EXERCISE_PATH, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> addExercise(@RequestBody @Valid AddExerciseDto addExerciseDto) {
        log.info(ADD_EXERCISE_PATH + " invoked.");

        Exercise exercise = new Exercise();
        exercise.setName(addExerciseDto.getName());
        exercise.setMuscleGroups(addExerciseDto.getMuscleGroups());
        exercise.setImage(exercise.getImage());

        exerciseService.save(exercise);

        return ResponseEntity.ok().build();
    }

    @PostMapping(path = UPDATE_EXERCISE_PATH, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> updateExercise(@RequestBody @Valid UpdateExerciseDto updateExerciseDto) {
        log.info(UPDATE_EXERCISE_PATH + " invoked.");

        var exercise = exerciseService.getById(updateExerciseDto.getExerciseId()).orElseThrow(() -> new ExerciseNotFoundException(updateExerciseDto.getExerciseId()));
        exercise.setName(updateExerciseDto.getName());
        exercise.setMuscleGroups(updateExerciseDto.getMuscleGroups());
        exercise.setImage(updateExerciseDto.getImage());

        exerciseService.save(exercise);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = REMOVE_EXERCISE_PATH, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> removeExercise(@RequestBody @Valid RemoveExerciseDto removeExerciseDto) {
        log.info(REMOVE_EXERCISE_PATH + " invoked.");

        var exercise = exerciseService.getById(removeExerciseDto.getExerciseId()).orElseThrow(() -> new ExerciseNotFoundException(removeExerciseDto.getExerciseId()));
        exerciseService.delete(exercise);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = GET_WORKOUT_EXERCISES_PATH, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Exercise>> getAllWorkoutExercises() {
        log.info(GET_WORKOUT_EXERCISES_PATH + " invoked.");

        return ResponseEntity.ok(exerciseService.getAll());
    }
}
