package itmo.tech.lab5api.controllers;

import itmo.tech.lab5api.producers.CatKafkaProducer;
import itmo.tech.lab5api.utils.AccessChecker;
import itmo.tech.lab5domain.cat.CatDto;
import itmo.tech.lab5domain.cat.Color;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cats")
public class CatController {

    private final CatKafkaProducer kafkaProducer;
    private final AccessChecker accessChecker;

    @GetMapping("/{id}")
    public CatDto findCatById(
            @PathVariable @Positive Long id
    ) throws ExecutionException, InterruptedException {
        if (accessChecker.hasNoAccessToCat(id)) {
            throw new AccessDeniedException("You are not owner of this cat");
        }
        return kafkaProducer.sendFindByIdMessage(id);
    }

    @GetMapping
    public List<CatDto> findAllCats()
            throws ExecutionException, InterruptedException {
        return kafkaProducer.sendFindAllMessage();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CatDto saveCat(
            @RequestBody @Valid CatDto dto
    ) throws ExecutionException, InterruptedException {
        return kafkaProducer.sendSaveMessage(dto);
    }

    @PutMapping("/{id}")
    public CatDto updateCat(
            @PathVariable @Positive Long id,
            @RequestBody @Valid CatDto dto
    ) throws ExecutionException, InterruptedException {
        if (accessChecker.hasNoAccessToCat(id)) {
            throw new AccessDeniedException("You are not owner of this cat");
        }
        return kafkaProducer.sendUpdateMessage(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCat(
            @PathVariable @Positive Long id
    ) {
        if (accessChecker.hasNoAccessToCat(id)) {
            throw new AccessDeniedException("You are not owner of this cat");
        }
        kafkaProducer.sendDeleteMessage(id);
    }

    @GetMapping("/with-color/{color}")
    public List<CatDto> findCatsByColor(
            @PathVariable Color color
    ) throws ExecutionException, InterruptedException {
        var cats = kafkaProducer.sendFindByColorMessage(color);
        return accessChecker.filterCatsByUser(cats);
    }

    @GetMapping("/with-breed/{breed}")
    public List<CatDto> findCatsByBreed(
            @PathVariable @NotBlank String breed
    ) throws ExecutionException, InterruptedException {
        var cats = kafkaProducer.sendFindByBreedMessage(breed);
        return accessChecker.filterCatsByUser(cats);
    }
}
