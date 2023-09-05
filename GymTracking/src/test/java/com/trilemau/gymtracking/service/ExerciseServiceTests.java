package com.trilemau.gymtracking.service;

import com.trilemau.gymtracking.GymTrackingApplication;
import com.trilemau.gymtracking.domain.entity.Exercise;
import com.trilemau.gymtracking.domain.enums.MuscleGroup;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan(basePackageClasses = {GymTrackingApplication.class})
@Transactional
@SpringBootTest
public class ExerciseServiceTests {

    @Inject
    private ExerciseService exerciseService;

    @Test
    void getAll() {
        var result = exerciseService.getAll();
        assertEquals(result.size(), 4);
    }

    @Test
    void getById_null() {
        assertThrows(IllegalArgumentException.class, () -> {
            var result = exerciseService.getById(null);
        });
    }

    @Test
    void getById_notExistingId() {
        var result = exerciseService.getById(9999L);
        assertFalse(result.isPresent());
    }

    @Test
    void getById_barbellBackSquat() {
        var barbellBackSquatId = 333L;
        var result = exerciseService.getById(barbellBackSquatId);

        assertTrue(result.isPresent());
        assertEquals(result.get().getId(), barbellBackSquatId);
        assertEquals(result.get().getName(), "Barbell back squat");
        assertNull(result.get().getImage());

        var muscleGroups = result.get().getMuscleGroups();
        assertTrue(Arrays.asList(MuscleGroup.QUADS, MuscleGroup.GLUTES).containsAll(muscleGroups));
    }

    @Test
    void getAllContainingSubtring_ChestPress() {
        var result = exerciseService.getAllContainingSubtring("chest");
        assertEquals(result.size(), 2);
    }

    @Test
    void getAllContainingSubtring_InvalidSubstring() {
        var result = exerciseService.getAllContainingSubtring("XXX");
        assertEquals(result.size(), 0);
    }

    @Test
    void getAllContainingSubtring_NullSubstring() {
        var result = exerciseService.getAllContainingSubtring(null);
        assertEquals(result.size(), 0);
    }

    @Test
    void addExercise_Ok() {
        Exercise exercise = new Exercise();
        exercise.setName("New exercise");
        exercise.getMuscleGroups().add(MuscleGroup.ABS);
        exercise.getMuscleGroups().add(MuscleGroup.CALVES);

        var savedExercise = exerciseService.save(exercise);
        var getExercise = exerciseService.getById(savedExercise.getId());

        assertTrue(getExercise.isPresent());
        assertEquals(getExercise.get(), savedExercise);
    }

    @Test
    void addExercise_null() {
        assertThrows(IllegalArgumentException.class, () -> {
            var exercise = exerciseService.save(null);
        });
    }

    @Test
    void removeExercise_null() {
        assertThrows(IllegalArgumentException.class, () -> {
            exerciseService.delete(null);
        });
    }

    @Test
    void removeExercise_ok() {
        var barbellBackSquatId = 333L;
        var get = exerciseService.getById(barbellBackSquatId);

        assertTrue(get.isPresent());

        exerciseService.delete(get.get());

        get = exerciseService.getById(barbellBackSquatId);
        assertFalse(get.isPresent());
    }
}
