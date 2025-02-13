package itmo.tech.lab5domain.kafka.requests;

import itmo.tech.lab5domain.cat.CatDto;

public record UpdateCatRequest(Long id, CatDto cat) {
}
