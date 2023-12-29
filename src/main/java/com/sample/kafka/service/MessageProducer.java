package com.sample.kafka.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {
    private final String topicName;
    private final String topicNameDLT;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public MessageProducer(
            @Value("${kafka.topicName}") String topicName,
            @Value("${kafka.topicNameDLT}") String topicNameDLT,
            KafkaTemplate<String, String> kafkaTemplate) {
        this.topicName = topicName;
        this.topicNameDLT = topicNameDLT;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTopic(String message) {
        kafkaTemplate.send(topicName, message);
    }

    public void sendDLT(String message) {
        kafkaTemplate.send(topicNameDLT, message);
    }
}
