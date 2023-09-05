package com.trilemau.gymtracking.service;

import com.trilemau.gymtracking.GymTrackingApplication;
import com.trilemau.gymtracking.domain.entity.Exercise;
import com.trilemau.gymtracking.domain.entity.ExerciseSet;
import com.trilemau.gymtracking.domain.entity.User;
import com.trilemau.gymtracking.domain.entity.Workout;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan(basePackageClasses = {GymTrackingApplication.class})
@Transactional
@SpringBootTest
public class WorkoutServiceTests {

    @Inject
    private WorkoutService workoutService;

    @Inject
    private ExerciseService exerciseService;

    @Inject
    private ExerciseSetService exerciseSetService;

    @Inject
    private UserService userService;

    private User user;

    private Exercise flatDumbellChestPress;

    @BeforeEach
    void setup() {
        user = userService.getById(1L).orElseThrow(() -> new IllegalArgumentException("Failed to retrieve user"));
        flatDumbellChestPress = exerciseService.getById(111L).orElseThrow(() -> new IllegalArgumentException("Failed to retrieve exercise"));
    }

    @Test
    void addWorkoutAndExerciseSet() {
        Workout workout = new Workout();
        workout.setDate(LocalDate.of(2023, 1, 1));
        workout.setNotes("Random notes");

        user = userService.addWorkout(workout, user);
        workout = user.getWorkouts().get(0);

        ExerciseSet exerciseSet = new ExerciseSet();
        exerciseSet.setReps(10);
        exerciseSet.setWeight(37.5);
        exerciseSet.setExercise(flatDumbellChestPress);

        workout = workoutService.addWorkoutSet(exerciseSet, workout);
        exerciseSet = workout.getExerciseSets().get(0);

        var getUser = userService.getById(user.getId());
        assertTrue(getUser.isPresent());
        assertEquals(getUser.get().getWorkouts().size(), 1);

        var actualWorkout = getUser.get().getWorkouts().get(0);
        assertEquals(actualWorkout, workout);
        assertEquals(actualWorkout.getExerciseSets().size(), 1);

        var actualExerciseSet = actualWorkout.getExerciseSets().get(0);
        assertEquals(actualExerciseSet, exerciseSet);
    }
}
