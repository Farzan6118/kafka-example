package com.sample.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {

    private final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

    private final TaskExecutor exec = new SimpleAsyncTaskExecutor();

    @Bean
    public CommonErrorHandler errorHandler(KafkaOperations<Object, Object> template) {
        return new DefaultErrorHandler(
                new DeadLetterPublishingRecoverer(template), new FixedBackOff(1000L, 2));
    }

    @Bean
    public RecordMessageConverter converter() {
        return new JsonMessageConverter();
    }

    @KafkaListener(id = "${kafka.groupId}", topics = "${kafka.topicName}")
    public void listen(String foo) {
        logger.info("Received: " + foo);
//        this.exec.execute(() -> System.out.println("Hit Enter to terminate..."));
    }

    @KafkaListener(id = "${kafka.groupIdDLT}", topics = "${kafka.topicNameDLT}")
    public void dltListen(String in) {
        logger.info("Received from DLT: " + in);
//        this.exec.execute(() -> System.out.println("Hit Enter to terminate..."));
    }

    @Bean
    public NewTopic topic() {
        return new NewTopic("${kafka.topicName}}", 1, (short) 1);
    }

    @Bean
    public NewTopic dlt() {
        return new NewTopic("${kafka.topicNameDLT}", 1, (short) 1);
    }

    @Bean
//    @Profile("default") // Don't run from test(s)
    public ApplicationRunner runner() {
        return args -> {
//            System.out.println("Hit Enter to terminate...");
            System.in.read();
        };
    }
}
