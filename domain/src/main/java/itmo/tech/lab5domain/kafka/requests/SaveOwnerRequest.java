package itmo.tech.lab5domain.kafka.requests;

import itmo.tech.lab5domain.owner.OwnerDto;

public record SaveOwnerRequest(OwnerDto owner) {
}
