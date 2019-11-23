package com.sample.javaandkafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConsumerOne {

    @KafkaListener(id = "simple-consumer", topics = "simple-message")
    public void ConsumerOne(String message) {
        System.out.println(LocalDateTime.now() + " -> " + message);
    }
}
