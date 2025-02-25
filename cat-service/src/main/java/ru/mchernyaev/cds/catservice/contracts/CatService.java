package ru.mchernyaev.cds.catservice.contracts;

import ru.mchernyaev.cds.domain.kafka.replies.CatReply;
import ru.mchernyaev.cds.domain.kafka.replies.CatsReply;
import ru.mchernyaev.cds.domain.kafka.requests.*;

public interface CatService {
    CatReply findCatById(IdRequest request);

    CatsReply findAllCats();

    CatReply saveCat(SaveCatRequest request);

    CatReply updateCat(UpdateCatRequest request);

    void deleteCat(IdRequest request);

    CatsReply findCatsByColor(FindCatsByColorRequest request);

    CatsReply findCatsByBreed(FindCatsByBreedRequest request);
}
