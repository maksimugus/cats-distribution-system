package itmo.tech.lab5domain.kafka.replies;

import itmo.tech.lab5domain.owner.OwnerDto;

import java.util.List;

public record OwnersReply(List<OwnerDto> owners) {
}
