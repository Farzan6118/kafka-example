package com.sample.javaandkafka.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerOne {

    private KafkaTemplate<String, String> producerOne;

    public ProducerOne(KafkaTemplate<String, String> producerOne) {
        this.producerOne = producerOne;
    }

    public void send(String message) {
        producerOne.send("simple-message", message);
    }

}
