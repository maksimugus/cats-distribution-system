package itmo.tech.lab5api.security.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SignUpRequest(
        @NotBlank
        String login,
        @NotBlank
        String password,
        @Positive
        @NotNull
        Long ownerId
) {
}
