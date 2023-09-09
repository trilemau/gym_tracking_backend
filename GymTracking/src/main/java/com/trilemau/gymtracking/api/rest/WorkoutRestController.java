package com.trilemau.gymtracking.api.rest;

import com.trilemau.gymtracking.api.dto.workout.AddWorkoutDto;
import com.trilemau.gymtracking.api.dto.workout.GetAllUserWorkoutsDto;
import com.trilemau.gymtracking.api.dto.workout.RemoveWorkoutDto;
import com.trilemau.gymtracking.api.dto.workout.UpdateWorkoutDto;
import com.trilemau.gymtracking.domain.entity.Workout;
import com.trilemau.gymtracking.exception.UserNotFoundException;
import com.trilemau.gymtracking.exception.WorkoutNotFoundException;
import com.trilemau.gymtracking.service.UserService;
import com.trilemau.gymtracking.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
@RestController
public class WorkoutRestController {

    private static final String ADD_WORKOUT_PATH = "/v1/workout/add";
    private static final String UPDATE_WORKOUT_PATH = "/v1/workout/update";
    private static final String REMOVE_WORKOUT_PATH = "/v1/workout/remove";
    private static final String GET_USER_WORKOUTS_PATH = "/v1/workout/get-user-workouts";

    private final WorkoutService workoutService;
    private final UserService userService;

    // TODO exception handling

    @PostMapping(path = ADD_WORKOUT_PATH, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> addWorkout(@RequestBody @Valid AddWorkoutDto addWorkoutDto) {
        log.info(ADD_WORKOUT_PATH + " invoked.");

        var user = userService.getById(addWorkoutDto.getUserId()).orElseThrow(() -> new UserNotFoundException(addWorkoutDto.getUserId()));

        Workout workout = new Workout();
        workout.setDate(LocalDate.now());
        workout.setNotes("");

        userService.addWorkout(workout, user);

        return ResponseEntity.ok().build();
    }

    @PostMapping(path = UPDATE_WORKOUT_PATH, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> updateWorkout(@RequestBody @Valid UpdateWorkoutDto updateWorkoutDto) {
        log.info(UPDATE_WORKOUT_PATH + " invoked.");

        var workout = workoutService.getById(updateWorkoutDto.getId()).orElseThrow(() -> new WorkoutNotFoundException(updateWorkoutDto.getId()));
        workout.setDate(updateWorkoutDto.getDate());
        workout.setNotes(updateWorkoutDto.getNotes());

        workoutService.save(workout);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = REMOVE_WORKOUT_PATH, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> removeWorkout(@RequestBody @Valid RemoveWorkoutDto removeWorkoutDto) {
        log.info(REMOVE_WORKOUT_PATH + " invoked.");

        var workout = workoutService.getById(removeWorkoutDto.getWorkoutId()).orElseThrow(() -> new WorkoutNotFoundException(removeWorkoutDto.getWorkoutId()));
        var user = userService.getById(removeWorkoutDto.getUserId()).orElseThrow(() -> new UserNotFoundException(removeWorkoutDto.getUserId()));

        userService.removeWorkout(workout, user);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = GET_USER_WORKOUTS_PATH, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Workout>> getUserWorkouts(@RequestBody @Valid GetAllUserWorkoutsDto getAllUserWorkoutsDto) {
        log.info(GET_USER_WORKOUTS_PATH + " invoked.");

        var user = this.userService.getById(getAllUserWorkoutsDto.getUserId()).orElseThrow(() -> new UserNotFoundException(getAllUserWorkoutsDto.getUserId()));

        return ResponseEntity.ok(workoutService.getUserWorkouts(user));
    }
}
