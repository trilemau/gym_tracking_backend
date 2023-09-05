package com.trilemau.gymtracking.service;

import com.trilemau.gymtracking.domain.entity.User;
import com.trilemau.gymtracking.domain.entity.Workout;
import com.trilemau.gymtracking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }

        return userRepository.findById(id);
    }

    public User addWorkout(Workout workout, User user) {
        if (workout == null) {
            throw new IllegalArgumentException("Workout is null");
        }

        if (user == null) {
            throw new IllegalArgumentException("User is null");
        }

        user.addWorkout(workout);
        return userRepository.save(user);
    }
}
