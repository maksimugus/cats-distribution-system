package itmo.tech.lab5domain.kafka.replies;

import itmo.tech.lab5domain.cat.CatDto;

import java.util.List;

public record CatsReply(List<CatDto> cats) {
}
