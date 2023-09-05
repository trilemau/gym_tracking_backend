package com.trilemau.gymtracking.api.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@Slf4j
public class GymTrackingRestController {

    private static final String HEALTH_CHECK_PATH = "/v1/health-check";

    @GetMapping(path = HEALTH_CHECK_PATH, produces = APPLICATION_JSON_VALUE)
    String healthCheck() {
        log.info("Health check endpoint called.");
        return "Gym tracking is healthy";
    }
}
