package com.example.demospringquartz.service;

import com.example.demospringquartz.payload.EmailRequest;
import com.example.demospringquartz.payload.EmailResponse;

public interface EmailSchedulerService {
    EmailResponse scheduleEmail(EmailRequest emailRequest);
}
