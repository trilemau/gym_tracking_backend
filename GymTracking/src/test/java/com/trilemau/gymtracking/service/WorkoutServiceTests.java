package com.trilemau.gymtracking.service;

import com.trilemau.gymtracking.GymTrackingApplication;
import com.trilemau.gymtracking.domain.entity.Exercise;
import com.trilemau.gymtracking.domain.entity.ExerciseSet;
import com.trilemau.gymtracking.domain.entity.User;
import com.trilemau.gymtracking.domain.entity.Workout;
import com.trilemau.gymtracking.exception.ExerciseSetNotInWorkoutException;
import com.trilemau.gymtracking.exception.WorkoutNotInUserException;
import jakarta.inject.Inject;
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

    private User user1;
    private User user2;
    private Exercise flatDumbellChestPress;
    private ExerciseSet exerciseSet1;
    private ExerciseSet exerciseSet2;
    private Workout workout1;
    private Workout workout2;

    @BeforeEach
    void setup() {
        user1 = userService.getById(111L).orElseThrow(() -> new IllegalArgumentException("Failed to retrieve user"));
        user2 = userService.getById(222L).orElseThrow(() -> new IllegalArgumentException("Failed to retrieve user"));
        flatDumbellChestPress = exerciseService.getById(111L).orElseThrow(() -> new IllegalArgumentException("Failed to retrieve exercise"));
        exerciseSet1 = exerciseSetService.getById(111L).orElseThrow(() -> new IllegalArgumentException("Failed to retrieve exercise set"));
        exerciseSet2 = exerciseSetService.getById(222L).orElseThrow(() -> new IllegalArgumentException("Failed to retrieve exercise set"));
        workout1 = workoutService.getById(111L).orElseThrow(() -> new IllegalArgumentException("Failed to retrieve workout"));
        workout2 = workoutService.getById(222L).orElseThrow(() -> new IllegalArgumentException("Failed to retrieve workout"));
    }

    @Test
    void addWorkoutAndExerciseSet_Ok() {
        Workout workout = new Workout();
        workout.setDate(LocalDate.of(2023, 1, 1));
        workout.setNotes("Random notes");

        userService.addWorkout(workout, user1);
        workout = user1.getWorkouts().get(0);

        ExerciseSet exerciseSet = new ExerciseSet();
        exerciseSet.setReps(10);
        exerciseSet.setWeight(37.5);
        exerciseSet.setExercise(flatDumbellChestPress);

        workoutService.addExerciseSet(exerciseSet, workout);
        exerciseSet = workout.getExerciseSets().get(0);

        var getUser = userService.getById(user1.getId());
        assertTrue(getUser.isPresent());
        assertEquals(getUser.get().getWorkouts().size(), 1);

        var actualWorkout = getUser.get().getWorkouts().get(0);
        assertEquals(actualWorkout, workout);
        assertEquals(actualWorkout.getExerciseSets().size(), 1);

        var actualExerciseSet = actualWorkout.getExerciseSets().get(0);
        assertEquals(actualExerciseSet, exerciseSet);
    }

    @Test
    void addExerciseToWorkout_exerciseIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            workoutService.addExerciseSet(null, workout2);
        });
    }

    @Test
    void addExerciseToWorkout_workoutIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            workoutService.addExerciseSet(exerciseSet2, null);
        });
    }

    @Test
    void addWorkoutToUser_exerciseIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.addWorkout(null, user1);
        });
    }

    @Test
    void addWorkoutToUser_UserIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.addWorkout(workout1, null);
        });
    }

    @Test
    void removeExerciseFromWorkout_Ok() {
        var getExerciseSet = exerciseSetService.getById(exerciseSet1.getId());
        assertTrue(getExerciseSet.isPresent());

        assertEquals(workout1.getExerciseSets().size(), 1);
        workoutService.removeExerciseSet(exerciseSet1, workout1);
        assertEquals(workout1.getExerciseSets().size(), 0);

        getExerciseSet = exerciseSetService.getById(exerciseSet1.getId());
        assertFalse(getExerciseSet.isPresent());
    }

    @Test
    void removeExerciseFromWorkout_exerciseNotInWorkout() {
        assertThrows(ExerciseSetNotInWorkoutException.class, () -> {
            workoutService.removeExerciseSet(exerciseSet1, workout2);
        });
    }

    @Test
    void removeExerciseFromWorkout_exerciseIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            workoutService.removeExerciseSet(null, workout2);
        });
    }

    @Test
    void removeExerciseFromWorkout_workoutIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            workoutService.removeExerciseSet(exerciseSet1, null);
        });
    }

    @Test
    void removeWorkoutFromUser_Ok() {
        var getWorkout = workoutService.getById(workout1.getId());
        assertTrue(getWorkout.isPresent());

        var getExerciseSet = exerciseSetService.getById(exerciseSet1.getId());
        assertTrue(getExerciseSet.isPresent());

        assertEquals(user2.getWorkouts().size(), 1);
        userService.removeWorkout(workout1, user2);
        assertEquals(user2.getWorkouts().size(), 0);

        getWorkout = workoutService.getById(workout1.getId());
        assertFalse(getWorkout.isPresent());

        getExerciseSet = exerciseSetService.getById(exerciseSet1.getId());
        assertFalse(getExerciseSet.isPresent());
    }

    @Test
    void removeWorkoutFromUser_workoutNotInUser() {
        assertThrows(WorkoutNotInUserException.class, () -> {
            userService.removeWorkout(workout1, user1);
        });
    }

    @Test
    void removeWorkoutFromUser_workoutIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.removeWorkout(null, user1);
        });
    }

    @Test
    void removeWorkoutFromUser_userIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.removeWorkout(workout1, null);
        });
    }

    @Test
    void getWorkoutsByUser_userNoWorkouts() {
        var workouts = workoutService.getUserWorkouts(user2);
        assertEquals(workouts.size(), 1);
        assertEquals(workouts.get(0), workout1);
    }

    @Test
    void getWorkoutsByUser_userWithWorkouts() {
        var workouts = workoutService.getUserWorkouts(user1);
        assertEquals(workouts.size(), 0);
    }

    @Test
    void getWorkoutsByUser_userIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            workoutService.getUserWorkouts(null);
        });
    }
}
