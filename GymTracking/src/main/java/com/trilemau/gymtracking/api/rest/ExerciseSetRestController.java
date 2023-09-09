package com.trilemau.gymtracking.api.rest;

import com.trilemau.gymtracking.api.dto.exerciseset.AddExerciseSetDto;
import com.trilemau.gymtracking.api.dto.exerciseset.GetAllWorkoutExerciseSetsDto;
import com.trilemau.gymtracking.api.dto.exerciseset.RemoveExerciseSetDto;
import com.trilemau.gymtracking.api.dto.exerciseset.UpdateExerciseSetDto;
import com.trilemau.gymtracking.domain.entity.ExerciseSet;
import com.trilemau.gymtracking.exception.ExerciseNotFoundException;
import com.trilemau.gymtracking.exception.ExerciseSetNotFoundException;
import com.trilemau.gymtracking.exception.WorkoutNotFoundException;
import com.trilemau.gymtracking.service.ExerciseService;
import com.trilemau.gymtracking.service.ExerciseSetService;
import com.trilemau.gymtracking.service.WorkoutService;
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
public class ExerciseSetRestController {

    private static final String ADD_EXERCISE_SET_PATH = "/v1/exercise-set/add";
    private static final String UPDATE_EXERCISE_SET_PATH = "/v1/exercise-set/update";
    private static final String REMOVE_EXERCISE_SET_PATH = "/v1/exercise-set/remove";
    private static final String GET_WORKOUT_EXERCISE_SETS_PATH = "/v1/exercise-set/get-workout-sets";

    private final ExerciseService exerciseService;
    private final ExerciseSetService exerciseSetService;
    private final WorkoutService workoutService;

// TODO exception handling

    @PostMapping(path = ADD_EXERCISE_SET_PATH, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> addExerciseSet(@RequestBody @Valid AddExerciseSetDto addExerciseSetDto) {
        log.info(ADD_EXERCISE_SET_PATH + " invoked.");

        var workout = workoutService.getById(addExerciseSetDto.getWorkoutId()).orElseThrow(() -> new WorkoutNotFoundException(addExerciseSetDto.getWorkoutId()));
        var exercise = exerciseService.getById(addExerciseSetDto.getExerciseId()).orElseThrow(() -> new ExerciseNotFoundException(addExerciseSetDto.getExerciseId()));

        ExerciseSet exerciseSet = new ExerciseSet();
        exerciseSet.setExercise(exercise);
        exerciseSet.setReps(addExerciseSetDto.getReps());
        exerciseSet.setWeight(addExerciseSetDto.getWeight());

        workoutService.addExerciseSet(exerciseSet, workout);

        return ResponseEntity.ok().build();
    }

    @PostMapping(path = UPDATE_EXERCISE_SET_PATH, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> updateExerciseSet(@RequestBody @Valid UpdateExerciseSetDto updateExerciseSetDto) {
        log.info(UPDATE_EXERCISE_SET_PATH + " invoked.");

        var exercise = exerciseService.getById(updateExerciseSetDto.getExerciseId()).orElseThrow(() -> new ExerciseNotFoundException(updateExerciseSetDto.getExerciseId()));

        var exerciseSet = exerciseSetService.getById(updateExerciseSetDto.getExerciseSetId()).orElseThrow(() -> new ExerciseSetNotFoundException(updateExerciseSetDto.getExerciseSetId()));
        exerciseSet.setExercise(exercise);
        exerciseSet.setReps(updateExerciseSetDto.getReps());
        exerciseSet.setWeight(updateExerciseSetDto.getWeight());

        exerciseSetService.save(exerciseSet);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = REMOVE_EXERCISE_SET_PATH, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> removeExerciseSet(@RequestBody @Valid RemoveExerciseSetDto removeExerciseSetDto) {
        log.info(REMOVE_EXERCISE_SET_PATH + " invoked.");

        var exerciseSet = exerciseSetService.getById(removeExerciseSetDto.getExerciseSetId()).orElseThrow(() -> new ExerciseSetNotFoundException(removeExerciseSetDto.getExerciseSetId()));
        var workout = workoutService.getById(removeExerciseSetDto.getWorkoutId()).orElseThrow(() -> new WorkoutNotFoundException(removeExerciseSetDto.getWorkoutId()));

        workoutService.removeExerciseSet(exerciseSet, workout);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = GET_WORKOUT_EXERCISE_SETS_PATH, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<ExerciseSet>> getAllWorkoutExerciseSets(@RequestBody @Valid GetAllWorkoutExerciseSetsDto getAllWorkoutExerciseSetsDto) {
        log.info(GET_WORKOUT_EXERCISE_SETS_PATH + " invoked.");

        var workout = workoutService.getById(getAllWorkoutExerciseSetsDto.getWorkoutId()).orElseThrow(() -> new WorkoutNotFoundException(getAllWorkoutExerciseSetsDto.getWorkoutId()));

        return ResponseEntity.ok(exerciseSetService.getWorkoutExerciseSets(workout));
    }
}
