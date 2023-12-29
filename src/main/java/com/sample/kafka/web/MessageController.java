package com.sample.kafka.web;

import com.sample.kafka.service.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class MessageController {

    private final MessageProducer messageProducer;

    @Autowired
    public MessageController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping(path = "/send/topic/{what}")
    public void sendTopic(@PathVariable String what) {
        messageProducer.sendTopic(what);
    }

    @PostMapping(path = "/send/dlt/{what}")
    public void sendDLT(@PathVariable String what) {
        messageProducer.sendDLT(what);
    }
}
