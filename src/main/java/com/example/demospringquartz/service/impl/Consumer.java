package com.example.demospringquartz.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Consumer {
    @KafkaListener(topics = "myTopic", groupId = "myGroup")
    public void consumerFromTopic(String message) {
        log.info("Consummed message " + message);
    }
}
