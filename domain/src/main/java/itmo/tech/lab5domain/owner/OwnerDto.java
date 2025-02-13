package itmo.tech.lab5domain.owner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record OwnerDto(
        @NotBlank
        String name,
        @Past
        LocalDate birthDate,
        List<Long> catsIds
) {
    public static OwnerDto empty() {
        return builder().build();
    }
}
