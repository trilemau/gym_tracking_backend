package com.trilemau.gymtracking.service;

import com.trilemau.gymtracking.domain.entity.Exercise;
import com.trilemau.gymtracking.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public Exercise save(Exercise exercise) {
        if (exercise == null) {
            throw new IllegalArgumentException("Exercise is null");
        }

        return exerciseRepository.save(exercise);
    }

    public void delete(Exercise exercise) {
        if (exercise == null) {
            throw new IllegalArgumentException("Exercise is null");
        }

        exerciseRepository.delete(exercise);
    }

    public Optional<Exercise> getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }

        return exerciseRepository.findById(id);
    }

    public List<Exercise> getAll() {
        return exerciseRepository.findAll();
    }

    public List<Exercise> getAllContainingSubtring(String substring) {
        return exerciseRepository.findAllByNameContains(substring);
    }
}
