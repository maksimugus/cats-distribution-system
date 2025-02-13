package itmo.tech.lab5owner.services;

import itmo.tech.lab5domain.Mapper;
import itmo.tech.lab5domain.kafka.replies.OwnerReply;
import itmo.tech.lab5domain.kafka.replies.OwnersReply;
import itmo.tech.lab5domain.kafka.requests.IdRequest;
import itmo.tech.lab5domain.kafka.requests.SaveOwnerRequest;
import itmo.tech.lab5domain.kafka.requests.UpdateOwnerRequest;
import itmo.tech.lab5domain.owner.Owner;
import itmo.tech.lab5domain.owner.OwnerDto;
import itmo.tech.lab5owner.contracts.OwnerRepository;
import itmo.tech.lab5owner.contracts.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final Mapper<Owner, OwnerDto> ownerMapper;

    @Override
    @KafkaListener(topics = "findOwnerById")
    @SendTo("findOwnerByIdReplyTopic")
    public OwnerReply findOwnerById(IdRequest request) {
        var ownerDto = ownerRepository.findById(request.id())
                .map(ownerMapper::toDto)
                .orElse(OwnerDto.empty());
        return new OwnerReply(ownerDto);
    }

    @Override
    @KafkaListener(topics = "findAllOwners")
    @SendTo("findAllOwnersReplyTopic")
    public OwnersReply findAllOwners() {
        var owners = ownerRepository.findAll()
                .stream()
                .map(ownerMapper::toDto)
                .toList();
        return new OwnersReply(owners);
    }

    @Override
    @KafkaListener(topics = "saveOwner")
    @SendTo("saveOwnerReplyTopic")
    public OwnerReply saveOwner(SaveOwnerRequest request) {
        var owner = ownerMapper.toEntity(request.owner());
        var savedOwner = ownerRepository.save(owner);
        var ownerDto = ownerMapper.toDto(savedOwner);
        return new OwnerReply(ownerDto);
    }

    @Override
    @KafkaListener(topics = "updateOwner")
    @SendTo("updateOwnerReplyTopic")
    public OwnerReply updateOwner(UpdateOwnerRequest request) {
        var owner = ownerMapper.toEntity(request.owner());
        owner.setId(request.id());
        var updatedOwner = ownerRepository.save(owner);
        var ownerDto = ownerMapper.toDto(updatedOwner);
        return new OwnerReply(ownerDto);
    }

    @Override
    @KafkaListener(topics = "deleteOwner")
    public void deleteOwner(IdRequest request) {
        ownerRepository.deleteById(request.id());
    }
}
