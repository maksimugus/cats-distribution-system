package itmo.tech.lab5api.config;

import itmo.tech.lab5domain.kafka.replies.CatReply;
import itmo.tech.lab5domain.kafka.replies.CatsReply;
import itmo.tech.lab5domain.kafka.replies.OwnersReply;
import itmo.tech.lab5domain.kafka.replies.OwnerReply;
import itmo.tech.lab5domain.kafka.requests.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaTemplateConfig {

    @Bean
    public ReplyingKafkaTemplate<String, IdRequest, CatReply> findCatByIdTemplate(
            ProducerFactory<String, IdRequest> producerFactory,
            ConcurrentKafkaListenerContainerFactory<String, CatReply> containerFactory
    ) {
        return replyingKafkaTemplate(producerFactory, containerFactory, "findCatByIdReplyTopic");
    }

    @Bean
    public ReplyingKafkaTemplate<String, SaveCatRequest, CatReply> saveCatTemplate(
            ProducerFactory<String, SaveCatRequest> producerFactory,
            ConcurrentKafkaListenerContainerFactory<String, CatReply> containerFactory
    ) {
        return replyingKafkaTemplate(producerFactory, containerFactory, "saveCatReplyTopic");
    }

    @Bean
    public ReplyingKafkaTemplate<String, UpdateCatRequest, CatReply> updateCatTemplate(
            ProducerFactory<String, UpdateCatRequest> producerFactory,
            ConcurrentKafkaListenerContainerFactory<String, CatReply> containerFactory
    ) {
        return replyingKafkaTemplate(producerFactory, containerFactory, "updateCatReplyTopic");
    }

    @Bean
    public ReplyingKafkaTemplate<String, FindAllRequest, CatsReply> findAllCatsTemplate(
            ProducerFactory<String, FindAllRequest> producerFactory,
            ConcurrentKafkaListenerContainerFactory<String, CatsReply> containerFactory
    ) {
        return replyingKafkaTemplate(producerFactory, containerFactory, "findAllCatsReplyTopic");
    }

    @Bean
    public ReplyingKafkaTemplate<String, FindCatsByColorRequest, CatsReply> findCatsByColorTemplate(
            ProducerFactory<String, FindCatsByColorRequest> producerFactory,
            ConcurrentKafkaListenerContainerFactory<String, CatsReply> containerFactory
    ) {
        return replyingKafkaTemplate(producerFactory, containerFactory, "findCatsByColorReplyTopic");
    }

    @Bean
    public ReplyingKafkaTemplate<String, FindCatsByBreedRequest, CatsReply> findCatsByBreedTemplate(
            ProducerFactory<String, FindCatsByBreedRequest> producerFactory,
            ConcurrentKafkaListenerContainerFactory<String, CatsReply> containerFactory
    ) {
        return replyingKafkaTemplate(producerFactory, containerFactory, "findCatsByBreedReplyTopic");
    }

    @Bean
    public ReplyingKafkaTemplate<String, IdRequest, OwnerReply> findOwnerByIdTemplate(
            ProducerFactory<String, IdRequest> producerFactory,
            ConcurrentKafkaListenerContainerFactory<String, OwnerReply> containerFactory
    ) {
        return replyingKafkaTemplate(producerFactory, containerFactory, "findOwnerByIdReplyTopic");
    }

    @Bean
    public ReplyingKafkaTemplate<String, FindAllRequest, OwnersReply> findAllOwnersTemplate(
            ProducerFactory<String, FindAllRequest> producerFactory,
            ConcurrentKafkaListenerContainerFactory<String, OwnersReply> containerFactory
    ) {
        return replyingKafkaTemplate(producerFactory, containerFactory, "findAllOwnersReplyTopic");
    }

    @Bean
    public ReplyingKafkaTemplate<String, SaveOwnerRequest, OwnerReply> saveOwnerTemplate(
            ProducerFactory<String, SaveOwnerRequest> producerFactory,
            ConcurrentKafkaListenerContainerFactory<String, OwnerReply> containerFactory
    ) {
        return replyingKafkaTemplate(producerFactory, containerFactory, "saveOwnerReplyTopic");
    }

    @Bean
    public ReplyingKafkaTemplate<String, UpdateOwnerRequest, OwnerReply> updateOwnerTemplate(
            ProducerFactory<String, UpdateOwnerRequest> producerFactory,
            ConcurrentKafkaListenerContainerFactory<String, OwnerReply> containerFactory
    ) {
        return replyingKafkaTemplate(producerFactory, containerFactory, "updateOwnerReplyTopic");
    }

    @Bean
    public KafkaTemplate<String, IdRequest> deleteTemplate(
            ProducerFactory<String, IdRequest> producerFactory
    ) {
        return new KafkaTemplate<>(producerFactory);
    }

    private static <V, R> ReplyingKafkaTemplate<String, V, R> replyingKafkaTemplate(
            ProducerFactory<String, V> producerFactory,
            ConcurrentKafkaListenerContainerFactory<String, R> containerFactory,
            String replyTopic
    ) {
        ConcurrentMessageListenerContainer<String, R> replyContainer =
                containerFactory.createContainer(replyTopic);
        replyContainer.getContainerProperties().setMissingTopicsFatal(false);
        return new ReplyingKafkaTemplate<>(producerFactory, replyContainer);
    }
}