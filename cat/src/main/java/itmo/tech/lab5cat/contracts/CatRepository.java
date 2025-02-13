package itmo.tech.lab5cat.contracts;

import itmo.tech.lab5domain.cat.Cat;
import itmo.tech.lab5domain.cat.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Long> {
    List<Cat> findByBreed(String breed);
    List<Cat> findByColor(Color color);
}
