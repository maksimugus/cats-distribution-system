package ru.mchernyaev.cds.api.security.auth;

import ru.mchernyaev.cds.api.security.config.JwtService;
import ru.mchernyaev.cds.api.security.user.UserService;
import ru.mchernyaev.cds.domain.owner.Owner;
import ru.mchernyaev.cds.domain.user.Role;
import ru.mchernyaev.cds.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        var owner = new Owner();
        owner.setId(signUpRequest.ownerId());
        var user = User.builder()
                .login(signUpRequest.login())
                .password(passwordEncoder.encode(signUpRequest.password()))
                .role(Role.ROLE_USER)
                .owner(owner)
                .build();
        var userDto = userService.saveUser(user);
        var jwt = jwtService.generateToken(userDto);
        return new AuthenticationResponse(jwt);
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        var token = new UsernamePasswordAuthenticationToken(
                signInRequest.login(),
                signInRequest.password()
        );
        authenticationManager.authenticate(token);
        var userDto = userService.loadUserByUsername(signInRequest.login());
        var jwt = jwtService.generateToken(userDto);
        return new AuthenticationResponse(jwt);
    }
}
