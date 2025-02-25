package ru.mchernyaev.cds.api.producers;

import ru.mchernyaev.cds.domain.kafka.replies.OwnersReply;
import ru.mchernyaev.cds.domain.kafka.replies.OwnerReply;
import ru.mchernyaev.cds.domain.kafka.requests.FindAllRequest;
import ru.mchernyaev.cds.domain.kafka.requests.IdRequest;
import ru.mchernyaev.cds.domain.kafka.requests.SaveOwnerRequest;
import ru.mchernyaev.cds.domain.kafka.requests.UpdateOwnerRequest;
import ru.mchernyaev.cds.domain.owner.OwnerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class OwnerKafkaProducer extends AbstractKafkaProducer {

    private final ReplyingKafkaTemplate<String, IdRequest, OwnerReply> findByIdTemplate;
    private final ReplyingKafkaTemplate<String, SaveOwnerRequest, OwnerReply> saveTemplate;
    private final ReplyingKafkaTemplate<String, UpdateOwnerRequest, OwnerReply> updateTemplate;
    private final ReplyingKafkaTemplate<String, FindAllRequest, OwnersReply> findAllTemplate;

    public OwnerDto sendFindByIdMessage(Long id)
            throws ExecutionException, InterruptedException {
        var request = new IdRequest(id);
        return send("findOwnerById", request, findByIdTemplate).owner();
    }

    public OwnerDto sendSaveMessage(OwnerDto ownerDto)
            throws ExecutionException, InterruptedException {

        var request = new SaveOwnerRequest(ownerDto);
        return send("saveOwner", request, saveTemplate).owner();
    }

    public OwnerDto sendUpdateMessage(Long id, OwnerDto ownerDto)
            throws ExecutionException, InterruptedException {
        var request = new UpdateOwnerRequest(id, ownerDto);
        return send("updateOwner", request, updateTemplate).owner();
    }

    public List<OwnerDto> sendFindAllMessage()
            throws ExecutionException, InterruptedException {
        var request = new FindAllRequest("");
        return send("findAllOwners", request, findAllTemplate).owners();
    }

    public void sendDeleteMessage(Long id) {
        var request = new IdRequest(id);
        super.sendDeleteMessage("deleteOwner", request);
    }
}
