package itmo.tech.lab5domain;

public interface Mapper<E, D> {
    D toDto(E entity);

    E toEntity(D dto);
}
