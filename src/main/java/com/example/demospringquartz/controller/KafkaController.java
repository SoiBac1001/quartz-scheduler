package com.example.demospringquartz.controller;

import com.example.demospringquartz.service.impl.Producer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class KafkaController {
    private final Producer producer;

    @PostMapping("/kafka")
    public ResponseEntity<Void> scheduleEmail(@RequestParam(value = "msg") String message) {
        producer.publishToTopic(message);
        return ResponseEntity.noContent().build();
    }
}
