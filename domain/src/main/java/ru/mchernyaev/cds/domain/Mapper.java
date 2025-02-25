package ru.mchernyaev.cds.domain;

public interface Mapper<E, D> {
    D toDto(E entity);

    E toEntity(D dto);
}
