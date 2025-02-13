package itmo.tech.lab5owner.services;

import itmo.tech.lab5domain.Mapper;
import itmo.tech.lab5domain.cat.Cat;
import itmo.tech.lab5domain.owner.Owner;
import itmo.tech.lab5domain.owner.OwnerDto;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class OwnerMapper implements Mapper<Owner, OwnerDto> {

    @Override
    public OwnerDto toDto(Owner entity) {
        Objects.requireNonNull(entity);

        List<Long> catsIds = Objects.isNull(entity.getCats())
                ? Collections.emptyList()
                : entity.getCats()
                .stream()
                .map(Cat::getId)
                .toList();

        return new OwnerDto(
                entity.getName(),
                entity.getBirthDate(),
                catsIds
        );
    }

    @Override
    public Owner toEntity(OwnerDto dto) {
        Objects.requireNonNull(dto);

        List<Cat> cats = Objects.isNull(dto.catsIds())
                ? Collections.emptyList()
                : dto.catsIds()
                .stream()
                .map(id -> Cat.builder().id(id).build())
                .toList();

        return Owner.builder()
                .name(dto.name())
                .birthDate(dto.birthDate())
                .cats(cats)
                .build();
    }
}
