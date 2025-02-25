package ru.mchernyaev.cds.api.utils;

import ru.mchernyaev.cds.api.security.user.UserService;
import ru.mchernyaev.cds.domain.cat.CatDto;
import ru.mchernyaev.cds.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AccessChecker {

    private final UserService userService;

    public boolean hasNoAccessToCat(Long catId) {
        return !isAdmin() && !isCatOwner(catId);
    }

    public boolean isAdmin() {
        var role = userService.getCurrentUser().role();
        return Objects.equals(role, Role.ROLE_ADMIN);
    }

    public boolean isCatOwner(Long catId) {
        return userService.getCurrentUser()
                .catsIds()
                .stream()
                .anyMatch(id -> id.equals(catId));
    }

    public List<CatDto> filterCatsByUser(List<CatDto> cats) {
        if (isAdmin()) {
            return cats;
        }
        var ownerId = userService.getCurrentUser().ownerId();
        return cats.stream()
                .filter(cat -> Objects.equals(cat.ownerId(), ownerId))
                .toList();
    }

    public boolean hasNoAccessToOwner(Long id) {
        var ownerId = userService.getCurrentUser().ownerId();
        return !isAdmin() && !Objects.equals(id, ownerId);
    }
}
