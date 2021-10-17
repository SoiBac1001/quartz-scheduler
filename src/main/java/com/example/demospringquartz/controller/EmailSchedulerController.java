package com.example.demospringquartz.controller;

import com.example.demospringquartz.payload.EmailRequest;
import com.example.demospringquartz.payload.EmailResponse;
import com.example.demospringquartz.service.EmailSchedulerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Slf4j
public class EmailSchedulerController {
    private final EmailSchedulerService emailSchedulerService;

    @PostMapping("/schedule/email")
    public ResponseEntity<EmailResponse> scheduleEmail(@RequestBody @Valid EmailRequest emailRequest) {
        return new ResponseEntity<>(this.emailSchedulerService.scheduleEmail(emailRequest), HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Get API test - pass");
    }
}
