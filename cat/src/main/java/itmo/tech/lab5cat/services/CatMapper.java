package itmo.tech.lab5cat.services;

import itmo.tech.lab5domain.Mapper;
import itmo.tech.lab5domain.cat.Cat;
import itmo.tech.lab5domain.cat.CatDto;
import itmo.tech.lab5domain.owner.Owner;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@Service
public class CatMapper implements Mapper<Cat, CatDto> {

    @Override
    public CatDto toDto(Cat entity) {
        Objects.requireNonNull(entity);

        var owner = entity.getOwner();
        var ownerId = isNull(owner)
                ? null
                : owner.getId();

        var friendsIds = mapFriendsEntitiesToIds(entity);

        return new CatDto(
                entity.getName(),
                entity.getBirthDate(),
                entity.getBreed(),
                entity.getColor(),
                ownerId,
                friendsIds
        );
    }

    private List<Long> mapFriendsEntitiesToIds(Cat cat) {
        var friends = cat.getFriends();
        return isNull(friends)
                ? Collections.emptyList()
                : friends.stream()
                .map(Cat::getId)
                .toList();
    }

    @Override
    public Cat toEntity(CatDto dto) {
        Objects.requireNonNull(dto);

        var ownerId = dto.ownerId();
        Owner owner = null;
        if (Objects.nonNull(ownerId)) {
            owner = new Owner();
            owner.setId(ownerId);
        }

        var friends = mapFriendsIdsToEntities(dto);

        return Cat.builder()
                .name(dto.name())
                .birthDate(dto.birthDate())
                .breed(dto.breed())
                .color(dto.color())
                .owner(owner)
                .friends(friends)
                .build();
    }

    private List<Cat> mapFriendsIdsToEntities(CatDto dto) {
        var friendsIds = dto.friendsIds();
        return isNull(friendsIds)
                ? Collections.emptyList()
                : friendsIds.stream()
                .map(id -> Cat.builder().id(id).build())
                .toList();
    }
}
