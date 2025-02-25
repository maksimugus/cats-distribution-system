package ru.mchernyaev.cds.catservice.contracts;

import ru.mchernyaev.cds.domain.cat.Cat;
import ru.mchernyaev.cds.domain.cat.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Long> {
    List<Cat> findByBreed(String breed);
    List<Cat> findByColor(Color color);
}
