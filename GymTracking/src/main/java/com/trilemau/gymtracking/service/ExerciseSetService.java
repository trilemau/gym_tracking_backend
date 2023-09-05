package com.trilemau.gymtracking.service;

import com.trilemau.gymtracking.domain.entity.ExerciseSet;
import com.trilemau.gymtracking.repository.ExerciseSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ExerciseSetService {

    private final ExerciseSetRepository exerciseSetRepository;

    // TODO not used
    public Optional<ExerciseSet> getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }

        return exerciseSetRepository.findById(id);
    }
}
