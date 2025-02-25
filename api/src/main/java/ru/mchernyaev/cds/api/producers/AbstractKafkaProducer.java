package ru.mchernyaev.cds.api.producers;

import ru.mchernyaev.cds.domain.kafka.requests.IdRequest;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
public abstract class AbstractKafkaProducer {

    @Autowired
    private KafkaTemplate<String, IdRequest> deleteTemplate;

    protected void sendDeleteMessage(String topic, IdRequest request) {
        deleteTemplate.send(topic, request);
    }

    protected static <V, R> R send(
            String topic,
            V value,
            ReplyingKafkaTemplate<String, V, R> replyingKafkaTemplate
    ) throws ExecutionException, InterruptedException {
        var record = new ProducerRecord<String, V>(topic, value);
        var future = replyingKafkaTemplate.sendAndReceive(record);
        var result = future.get();
        return result.value();
    }
}
