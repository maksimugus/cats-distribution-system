package ru.mchernyaev.cds.domain.kafka.requests;

import ru.mchernyaev.cds.domain.owner.OwnerDto;

public record UpdateOwnerRequest(Long id,OwnerDto owner) {
}
