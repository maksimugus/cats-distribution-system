package ru.mchernyaev.cds.domain.kafka.requests;

import ru.mchernyaev.cds.domain.cat.CatDto;

public record SaveCatRequest(CatDto cat) {
}
