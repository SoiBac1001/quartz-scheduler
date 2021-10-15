package com.example.demospringquartz.controller;

import com.example.demospringquartz.service.EmailSchedulerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class EmailSchedulerController {
    private final EmailSchedulerService emailSchedulerService;
}
