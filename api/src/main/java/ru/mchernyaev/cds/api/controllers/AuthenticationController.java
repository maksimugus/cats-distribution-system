package ru.mchernyaev.cds.api.controllers;

import ru.mchernyaev.cds.api.security.auth.AuthenticationResponse;
import ru.mchernyaev.cds.api.security.auth.AuthenticationService;
import ru.mchernyaev.cds.api.security.auth.SignInRequest;
import ru.mchernyaev.cds.api.security.auth.SignUpRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public AuthenticationResponse signUp(
            @RequestBody @Valid SignUpRequest signUpRequest
    ) {
        return authenticationService.signUp(signUpRequest);
    }

    @PostMapping("/sign-in")
    public AuthenticationResponse signIn(
            @RequestBody @Valid SignInRequest signInRequest
    ) {
        return authenticationService.signIn(signInRequest);
    }
}
