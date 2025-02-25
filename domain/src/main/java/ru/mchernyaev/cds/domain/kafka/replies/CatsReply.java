package ru.mchernyaev.cds.domain.kafka.replies;

import ru.mchernyaev.cds.domain.cat.CatDto;

import java.util.List;

public record CatsReply(List<CatDto> cats) {
}
