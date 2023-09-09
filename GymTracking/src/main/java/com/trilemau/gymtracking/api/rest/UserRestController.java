package com.trilemau.gymtracking.api.rest;

import com.trilemau.gymtracking.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserRestController {

    private static final String REGISTER_USER_PATH = "/v1/user/register";
    private static final String UPDATE_USER_PATH = "/v1/user/update";
    private static final String REMOVE_USER_PATH = "/v1/user/remove";
    private static final String LOGIN_USER_PATH = "/v1/user/login";

    private UserService userService;

    // TODO
}
