package itmo.tech.lab5cat.contracts;

import itmo.tech.lab5domain.kafka.replies.CatReply;
import itmo.tech.lab5domain.kafka.replies.CatsReply;
import itmo.tech.lab5domain.kafka.requests.*;

public interface CatService {
    CatReply findCatById(IdRequest request);

    CatsReply findAllCats();

    CatReply saveCat(SaveCatRequest request);

    CatReply updateCat(UpdateCatRequest request);

    void deleteCat(IdRequest request);

    CatsReply findCatsByColor(FindCatsByColorRequest request);

    CatsReply findCatsByBreed(FindCatsByBreedRequest request);
}
