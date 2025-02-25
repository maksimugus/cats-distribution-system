package ru.mchernyaev.cds.ownerservice.contracts;

import ru.mchernyaev.cds.domain.kafka.replies.OwnersReply;
import ru.mchernyaev.cds.domain.kafka.replies.OwnerReply;
import ru.mchernyaev.cds.domain.kafka.requests.IdRequest;
import ru.mchernyaev.cds.domain.kafka.requests.SaveOwnerRequest;
import ru.mchernyaev.cds.domain.kafka.requests.UpdateOwnerRequest;

public interface OwnerService {
    OwnerReply findOwnerById(IdRequest request);

    OwnersReply findAllOwners();

    OwnerReply saveOwner(SaveOwnerRequest request);

    OwnerReply updateOwner(UpdateOwnerRequest request);

    void deleteOwner(IdRequest request);
}
