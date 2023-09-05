package com.trilemau.gymtracking.api.rest;

import com.trilemau.gymtracking.api.dto.workout.AddWorkoutDto;
import com.trilemau.gymtracking.api.dto.workout.GetAllUserWorkoutsDto;
import com.trilemau.gymtracking.api.dto.workout.RemoveWorkoutDto;
import com.trilemau.gymtracking.api.dto.workout.UpdateWorkoutDto;
import com.trilemau.gymtracking.domain.entity.User;
import com.trilemau.gymtracking.domain.entity.Workout;
import com.trilemau.gymtracking.exception.UserNotFoundException;
import com.trilemau.gymtracking.exception.WorkoutNotFoundException;
import com.trilemau.gymtracking.service.UserService;
import com.trilemau.gymtracking.service.WorkoutService;
import jakarta.validation.Valid;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
public class WorkoutRestController {

    private static final String ADD_WORKOUT_PATH = "/v1/workout/add";
    private static final String UPDATE_WORKOUT_PATH = "/v1/workout/update";
    private static final String REMOVE_WORKOUT_PATH = "/v1/workout/remove";
    private static final String GET_ALL_USER_WORKOUTS_PATH = "/v1/workout/user-workouts";

    private final WorkoutService workoutService;
    private final UserService userService;

    private User user;

    public WorkoutRestController(WorkoutService workoutService, UserService userService) {
        this.workoutService = workoutService;
        this.userService = userService;
    }

    // TODO exception handling

    @PostMapping(path = ADD_WORKOUT_PATH, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> addWorkout(@RequestBody @Valid AddWorkoutDto addWorkoutDto) {
        log.info("Add workout endpoint called.");

        var user = userService.getById(addWorkoutDto.getUserId()).orElseThrow(() -> new UserNotFoundException(addWorkoutDto.getUserId()));

        Workout workout = new Workout();
        workout.setDate(LocalDate.now());
        workout.setNotes("");

        userService.addWorkout(workout, user);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = UPDATE_WORKOUT_PATH, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> updateWorkout(@RequestBody @Valid UpdateWorkoutDto updateWorkoutDto) {
        log.info("Update workout endpoint called.");

        var workout = workoutService.getById(updateWorkoutDto.getId()).orElseThrow(() -> new WorkoutNotFoundException(updateWorkoutDto.getId()));
        workout.setDate(updateWorkoutDto.getDate());
        workout.setNotes(updateWorkoutDto.getNotes());

        workoutService.save(workout);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = REMOVE_WORKOUT_PATH, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> removeWorkout(@RequestBody @Valid RemoveWorkoutDto removeWorkoutDto) {
        log.info("Remove workout endpoint called.");

        var workout = workoutService.getById(removeWorkoutDto.getWorkoutId()).orElseThrow(() -> new WorkoutNotFoundException(removeWorkoutDto.getWorkoutId()));
        var user = userService.getById(removeWorkoutDto.getUserId()).orElseThrow(() -> new UserNotFoundException(removeWorkoutDto.getUserId()));

        userService.removeWorkout(workout, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = GET_ALL_USER_WORKOUTS_PATH, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Workout>> getAllUserWorkouts(@RequestBody @Valid GetAllUserWorkoutsDto getAllUserWorkoutsDto) {
        log.info("Get all user workouts endpoint called.");

        user = this.userService.getById(getAllUserWorkoutsDto.getUserId()).orElseThrow(() -> new UserNotFoundException(getAllUserWorkoutsDto.getUserId()));
        return ResponseEntity.ok(workoutService.getWorkoutsByUser(user));
    }
}
