package ru.mchernyaev.cds.catservice.services;

import ru.mchernyaev.cds.catservice.contracts.CatRepository;
import ru.mchernyaev.cds.catservice.contracts.CatService;
import ru.mchernyaev.cds.domain.Mapper;
import ru.mchernyaev.cds.domain.cat.Cat;
import ru.mchernyaev.cds.domain.cat.CatDto;
import ru.mchernyaev.cds.domain.kafka.replies.CatReply;
import ru.mchernyaev.cds.domain.kafka.replies.CatsReply;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import ru.mchernyaev.cds.domain.kafka.requests.*;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final Mapper<Cat, CatDto> catMapper;

    @Override
    @KafkaListener(topics = "findCatById")
    @SendTo("findCatByIdReplyTopic")
    public CatReply findCatById(IdRequest request) {
        var catDto = catRepository.findById(request.id())
                .map(catMapper::toDto)
                .orElse(CatDto.empty());
        return new CatReply(catDto);
    }

    @Override
    @KafkaListener(topics = "findAllCats")
    @SendTo("findAllCatsReplyTopic")
    public CatsReply findAllCats() {
        var cats = catRepository.findAll()
                .stream()
                .map(catMapper::toDto)
                .toList();
        return new CatsReply(cats);
    }

    @Override
    @KafkaListener(topics = "saveCat")
    @SendTo("saveCatReplyTopic")
    public CatReply saveCat(SaveCatRequest request) {
        var cat = catMapper.toEntity(request.cat());
        var savedCat = catRepository.save(cat);
        var catDto = catMapper.toDto(savedCat);
        return new CatReply(catDto);
    }

    @Override
    @KafkaListener(topics = "updateCat")
    @SendTo("updateCatReplyTopic")
    public CatReply updateCat(UpdateCatRequest request) {
        var cat = catMapper.toEntity(request.cat());
        cat.setId(request.id());
        var updatedCat = catRepository.save(cat);
        var catDto = catMapper.toDto(updatedCat);
        return new CatReply(catDto);
    }

    @Override
    @KafkaListener(topics = "deleteCat")
    public void deleteCat(IdRequest request) {
        catRepository.deleteById(request.id());
    }

    @Override
    @KafkaListener(topics = "findCatsByColor")
    @SendTo("findCatsByColorReplyTopic")
    public CatsReply findCatsByColor(FindCatsByColorRequest request) {
        var cats = catRepository.findByColor(request.color())
                .stream()
                .map(catMapper::toDto)
                .toList();
        return new CatsReply(cats);
    }

    @Override
    @KafkaListener(topics = "findCatsByBreed")
    @SendTo("findCatsByBreedReplyTopic")
    public CatsReply findCatsByBreed(FindCatsByBreedRequest request) {
        var cats = catRepository.findByBreed(request.breed())
                .stream()
                .map(catMapper::toDto)
                .toList();
        return new CatsReply(cats);
    }
}
