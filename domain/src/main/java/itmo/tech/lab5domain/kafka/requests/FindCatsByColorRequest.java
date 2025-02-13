package itmo.tech.lab5domain.kafka.requests;

import itmo.tech.lab5domain.cat.Color;

public record FindCatsByColorRequest(Color color) {
}
