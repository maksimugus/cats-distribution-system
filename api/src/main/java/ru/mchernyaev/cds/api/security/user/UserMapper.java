package ru.mchernyaev.cds.api.security.user;

import ru.mchernyaev.cds.domain.cat.Cat;
import ru.mchernyaev.cds.domain.owner.Owner;
import ru.mchernyaev.cds.domain.user.User;
import ru.mchernyaev.cds.domain.user.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class UserMapper {
    public UserDto toDto(User user) {
        Owner owner = user.getOwner();
        List<Long> catsIds = List.of();
        Long ownerId = null;
        if (nonNull(owner)) {
            var cats = owner.getCats();
            if (nonNull(cats)) {
                catsIds = cats.stream()
                        .map(Cat::getId)
                        .toList();
            }
            ownerId = owner.getId();
        }
        return new UserDto(
                user.getLogin(),
                user.getPassword(),
                user.getRole(),
                ownerId,
                catsIds
        );
    }
}
