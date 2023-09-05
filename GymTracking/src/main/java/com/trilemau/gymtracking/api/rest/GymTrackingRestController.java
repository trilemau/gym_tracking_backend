package com.trilemau.gymtracking.api.rest;

import com.trilemau.gymtracking.domain.entity.Exercise;
import com.trilemau.gymtracking.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
public class GymTrackingRestController {

    private static final String HEALTH_CHECK_PATH = "/v1/health-check";

    @GetMapping(path = HEALTH_CHECK_PATH, produces = APPLICATION_JSON_VALUE)
    String healthCheck() {
        return "Gym tracking is healthy";
    }
}
