package com.example.demospringquartz.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class Producer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    public static final String topic = "myTopic";

    public void publishToTopic (String message) {
        log.info("Publishing to topic " + topic);
        this.kafkaTemplate.send(topic, message);
    }
}
