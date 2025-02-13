package itmo.tech.lab5api.producers;

import itmo.tech.lab5domain.cat.CatDto;
import itmo.tech.lab5domain.cat.Color;
import itmo.tech.lab5domain.kafka.replies.CatReply;
import itmo.tech.lab5domain.kafka.replies.CatsReply;
import itmo.tech.lab5domain.kafka.requests.*;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class CatKafkaProducer extends AbstractKafkaProducer {

    private final ReplyingKafkaTemplate<String, IdRequest, CatReply> findByIdTemplate;
    private final ReplyingKafkaTemplate<String, SaveCatRequest, CatReply> saveTemplate;
    private final ReplyingKafkaTemplate<String, UpdateCatRequest, CatReply> updateTemplate;
    private final ReplyingKafkaTemplate<String, FindAllRequest, CatsReply> findAllTemplate;
    private final ReplyingKafkaTemplate<String, FindCatsByColorRequest, CatsReply> findByColorTemplate;
    private final ReplyingKafkaTemplate<String, FindCatsByBreedRequest, CatsReply> findByBreedTemplate;

    public CatDto sendFindByIdMessage(Long id)
            throws ExecutionException, InterruptedException {
        var request = new IdRequest(id);
        return send("findCatById", request, findByIdTemplate).cat();
    }

    public List<CatDto> sendFindAllMessage()
            throws ExecutionException, InterruptedException {
        var request = new FindAllRequest("");
        return send("findAllCats", request, findAllTemplate).cats();
    }

    public CatDto sendSaveMessage(CatDto catDto)
            throws ExecutionException, InterruptedException {
        var request = new SaveCatRequest(catDto);
        return send("saveCat", request, saveTemplate).cat();
    }

    public CatDto sendUpdateMessage(Long id, CatDto catDto)
            throws ExecutionException, InterruptedException {
        var request = new UpdateCatRequest(id, catDto);
        return send("updateCat", request, updateTemplate).cat();
    }

    public List<CatDto> sendFindByColorMessage(Color color)
            throws ExecutionException, InterruptedException {
        var request = new FindCatsByColorRequest(color);
        return send("findCatsByColor", request, findByColorTemplate).cats();
    }

    public List<CatDto> sendFindByBreedMessage(String breed)
            throws ExecutionException, InterruptedException {
        var request = new FindCatsByBreedRequest(breed);
        return send("findCatsByBreed", request, findByBreedTemplate).cats();
    }

    public void sendDeleteMessage(Long id) {
        var request = new IdRequest(id);
        super.sendDeleteMessage("deleteCat", request);
    }
}
