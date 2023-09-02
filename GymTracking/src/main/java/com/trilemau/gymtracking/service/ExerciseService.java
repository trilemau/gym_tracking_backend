package com.trilemau.gymtracking.service;

import com.trilemau.gymtracking.model.Exercise;
import com.trilemau.gymtracking.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public Collection<Exercise> getAll() {
        return exerciseRepository.findAll();
    }
}
