package ru.mchernyaev.cds.domain.kafka.requests;

import ru.mchernyaev.cds.domain.cat.CatDto;

public record UpdateCatRequest(Long id, CatDto cat) {
}
