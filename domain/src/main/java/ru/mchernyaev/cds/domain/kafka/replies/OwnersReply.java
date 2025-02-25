package ru.mchernyaev.cds.domain.kafka.replies;

import ru.mchernyaev.cds.domain.owner.OwnerDto;

import java.util.List;

public record OwnersReply(List<OwnerDto> owners) {
}
