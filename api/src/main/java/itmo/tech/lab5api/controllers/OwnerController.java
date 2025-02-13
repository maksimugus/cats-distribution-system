package itmo.tech.lab5api.controllers;

import itmo.tech.lab5api.producers.OwnerKafkaProducer;
import itmo.tech.lab5api.utils.AccessChecker;
import itmo.tech.lab5domain.owner.OwnerDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerKafkaProducer kafkaProducer;
    private final AccessChecker accessChecker;

    @GetMapping("/{id}")
    public OwnerDto findOwnerById(
            @PathVariable @Positive Long id
    ) throws ExecutionException, InterruptedException {
        if (accessChecker.hasNoAccessToOwner(id)) {
            throw new AccessDeniedException("No access to owner");
        }
        return kafkaProducer.sendFindByIdMessage(id);
    }

    @GetMapping
    public List<OwnerDto> findAllOwners()
            throws ExecutionException, InterruptedException {
        return kafkaProducer.sendFindAllMessage();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerDto saveOwner(
            @RequestBody @Valid OwnerDto dto
    ) throws ExecutionException, InterruptedException {
        return kafkaProducer.sendSaveMessage(dto);
    }

    @PutMapping("/{id}")
    public OwnerDto updateOwner(
            @PathVariable @Positive Long id,
            @RequestBody @Valid OwnerDto dto
    ) throws ExecutionException, InterruptedException {
        if (accessChecker.hasNoAccessToOwner(id)) {
            throw new AccessDeniedException("No access to owner");
        }
        return kafkaProducer.sendUpdateMessage(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(
            @PathVariable @Positive Long id
    ) {
        kafkaProducer.sendDeleteMessage(id);
    }
}
