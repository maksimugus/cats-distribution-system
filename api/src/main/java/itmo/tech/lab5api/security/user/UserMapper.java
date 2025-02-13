package itmo.tech.lab5api.security.user;

import itmo.tech.lab5domain.cat.Cat;
import itmo.tech.lab5domain.owner.Owner;
import itmo.tech.lab5domain.user.User;
import itmo.tech.lab5domain.user.UserDto;
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
