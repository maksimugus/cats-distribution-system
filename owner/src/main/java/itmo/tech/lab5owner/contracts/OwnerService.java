package itmo.tech.lab5owner.contracts;

import itmo.tech.lab5domain.kafka.replies.OwnersReply;
import itmo.tech.lab5domain.kafka.replies.OwnerReply;
import itmo.tech.lab5domain.kafka.requests.IdRequest;
import itmo.tech.lab5domain.kafka.requests.SaveOwnerRequest;
import itmo.tech.lab5domain.kafka.requests.UpdateOwnerRequest;

public interface OwnerService {
    OwnerReply findOwnerById(IdRequest request);

    OwnersReply findAllOwners();

    OwnerReply saveOwner(SaveOwnerRequest request);

    OwnerReply updateOwner(UpdateOwnerRequest request);

    void deleteOwner(IdRequest request);
}
