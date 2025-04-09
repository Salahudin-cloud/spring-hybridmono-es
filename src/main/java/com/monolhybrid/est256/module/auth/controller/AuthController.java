package com.monolhybrid.est256.module.auth.controller;

import com.monolhybrid.est256.common.dto.WebResponse;
import com.monolhybrid.est256.module.auth.dto.UserLoginRequest;
import com.monolhybrid.est256.module.auth.dto.UserRegisterRequest;
import com.monolhybrid.est256.module.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Void> register(@Valid @RequestBody UserRegisterRequest request) {
        return authService.registerUser(request);
    }

    @PostMapping(
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String>login(@Valid @RequestBody UserLoginRequest request) {
        return authService.loginUser(request);
    }

}
