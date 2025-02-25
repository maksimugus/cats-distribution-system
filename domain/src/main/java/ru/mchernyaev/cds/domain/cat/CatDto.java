package ru.mchernyaev.cds.domain.cat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record CatDto(
        @NotBlank
        String name,
        @Past
        @NotNull
        LocalDate birthDate,
        @NotBlank
        String breed,
        @NotNull
        Color color,
        @Positive
        Long ownerId,
        List<Long> friendsIds
) {
    public static CatDto empty() {
        return builder().build();
    }
}
