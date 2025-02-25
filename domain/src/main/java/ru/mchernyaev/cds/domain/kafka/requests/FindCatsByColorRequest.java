package ru.mchernyaev.cds.domain.kafka.requests;

import ru.mchernyaev.cds.domain.cat.Color;

public record FindCatsByColorRequest(Color color) {
}
