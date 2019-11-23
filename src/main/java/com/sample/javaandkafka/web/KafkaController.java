package com.sample.javaandkafka.web;

import com.sample.javaandkafka.service.ProducerOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final ProducerOne producerOne;

    @Autowired
    public KafkaController(ProducerOne producerOne) {
        this.producerOne = producerOne;
    }

    @PostMapping("/message")
    public ResponseEntity<String> message(@RequestBody String message) {
        producerOne.send(message);
        return ResponseEntity.ok(" Message received: " + message);
    }
}
